package via.sdj3.sep_t3.service;

import com.google.rpc.Code;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.adapters.MapperImplementation;
import via.sdj3.sep_t3.model.Category;
import via.sdj3.sep_t3.model.enums.StatusEnum;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.CategoriesRegistry;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static via.sdj3.sep_t3.service.GrpcImplementationHelper.generateCustomError;

/**
 * Implementation of GRPC for the Posts
 */
@Slf4j
@GRpcService
public class PostGrpcImplementation extends postServiceGrpc.postServiceImplBase
{
    private final PostRegistry postRegistry;
    private final UserRegistry userRegistry;
    private final CategoriesRegistry categoriesRegistry;
    private final MapperImplementation mapper = MapperImplementation.INSTANCE;

    /**
     * Autowired constructor for dependency injection
     *
     * @param postRegistry       postCRUD
     * @param userRegistry       userCRUD
     * @param categoriesRegistry categoryCRUD
     */
    @Autowired
    public PostGrpcImplementation(PostRegistry postRegistry, UserRegistry userRegistry, CategoriesRegistry categoriesRegistry)
    {
        this.postRegistry = postRegistry;
        this.userRegistry = userRegistry;
        this.categoriesRegistry = categoriesRegistry;
    }

    /**
     * Gets all post from the database
     * @param request Empty, the server doesn't do anything with it
     * @param responseObserver contains all the posts
     */

    @Override
    public void getAllPosts(Empty request, StreamObserver<AllPosts> responseObserver)
    {
        log.info("new request for getting all posts");
        List<PostReadGrpcDto> allPosts =new ArrayList<>();
        postRegistry.findAll().forEach(post -> allPosts.add(post.convertToPostReadGrpcDto()));
        responseObserver.onNext(AllPosts.newBuilder().addAllPost(allPosts).build());
        responseObserver.onCompleted();
    }

    /**
     * Creates a post in the database
     * @param request dto we get from T2
     * @param responseObserver grpc needs this
     * @implNote after .onError don't call anything else
     */
    @Override
    public void createPost(PostCreationGrpcDto request, StreamObserver<PostReadGrpcDto> responseObserver)
    {
        log.info("new request for creating a new post with credentials \n"+request.toString());
        var newPost=mapper.convertFromCreatePostsGrpcDto(request);
        //todo blazor client chooses this from a dropdown list
        try
        {
            newPost.setCreationDate(LocalDateTime.now());
            if (categoriesRegistry.findById(request.getCategories()).isEmpty())throw new IllegalArgumentException("category not found in database");
            if (userRegistry.findById(request.getOwnerId()).isEmpty())throw new IllegalArgumentException("user not found in database");
            newPost.setTitle(request.getTitle());
            newPost.setCategory(categoriesRegistry.findById(request.getCategories()).get());
            newPost.setSellerid(userRegistry.findById(request.getOwnerId()).get());
            newPost.setStatus(StatusEnum.Active.getNumber());
            postRegistry.save(newPost);
            log.info("saved post: "+newPost);
            responseObserver.onNext(newPost.convertToPostReadGrpcDto());
            responseObserver.onCompleted();
        }catch (Exception e)
        {
            log.error("Cant create post due to invalid argument: "+e.getMessage());
            var status=generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT);
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    /**
     * Fetches a post using an id
     * @param request contains a message requesting to fetch a post using an id
     * @param responseObserver returns a message from the server confirming the return
     */
    @Override
    public void getPostById(GenericMessage request, StreamObserver<PostReadGrpcDto> responseObserver)
    {
        log.info("new request for getting post by id: "+ request.getMessage());
        var temp= postRegistry.findById(Integer.parseInt(request.getMessage()));
        if (temp.isPresent())
        {
            responseObserver.onNext(temp.get().convertToPostReadGrpcDto());
            responseObserver.onCompleted();
            return;
        }
        log.error("No post was found containing the id"+request.getMessage());
        var status=generateCustomError("No post found with this id: "+request.getMessage(),Code.INVALID_ARGUMENT);
        responseObserver.onError(StatusProto.toStatusRuntimeException(status));
    }

    /**
     * will update a post with changes
     * @param request contains a request to update a post
     * @param responseObserver returns a confirmation if the update was successful
     */
    @Override
    public void updatePost(PostReadGrpcDto request, StreamObserver<GenericMessage> responseObserver)
    {
        //todo
        log.info("new request for updating post by id: "+ request.getCategories());
        try
        {
            var description=request.getDescription();
            var location=request.getLocation();

            if (categoriesRegistry.findById(request.getCategories()).isEmpty())
                throw new IllegalArgumentException("Category referenced does not exist in database!");
            var category=categoriesRegistry.findById(request.getCategories()).get();
            var pictureUrl=request.getPicture();
            var price=request.getPrice();
            var id =request.getId();
            var title=request.getTitle();
            var status=request.getStatus();
            postRegistry.updatePostById(description,location,category,pictureUrl,price,title,status,id);
            log.info("Post was updated successfully" +request);

            responseObserver.onNext(GenericMessage.newBuilder().setMessage("Successfully updated post!").build());
            responseObserver.onCompleted();
        }
        catch (Exception e)
        {
            log.error("Category referenced does not exist in database"+ e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(),Code.INVALID_ARGUMENT)));
        }
    }

    /**
     * this method deletes posts made by the users
     * @param request it will send a message requesting the deletion of a post based on the post id
     * @param responseObserver it will return/answer with the removal of the post if the Ids match
     */
    @Override
    public void deletePost(GenericMessage request, StreamObserver<GenericMessage> responseObserver)
    {
        try
        {
            postRegistry.deleteById(Integer.parseInt(request.getMessage()));
            log.info("Post with id " + request.getMessage() + " was deleted");
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("true").build());
            responseObserver.onCompleted();
        } catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT)));
        }
    }

    /**
     * Gets the category for a given post
     * @param request GenericMessage contains the postId
     * @param responseObserver Response with the category
     */
    @Override
    public void getCategoryByPostId(GenericMessage request, StreamObserver<CategoryReadGrpcDto> responseObserver)
    {
        try
        {
            var id = Integer.parseInt(request.getMessage());
            log.info("Looking up category for postId " + id);
            var postFromDatabase = postRegistry.findById(id);
            if (postFromDatabase.isEmpty()) throw new IllegalArgumentException("Post not found");
            var category = categoriesRegistry.findById(postFromDatabase.get().getCategory().getId());
            responseObserver.onNext(category.get().convertToGrpcReadDto());
            responseObserver.onCompleted();
        } catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT)));
        }
    }

    /**
     * Method to get all categories from the database
     * @param request empty reuqest the server does not do anything with it
     * @param responseObserver contains the return value
     */
    @Override
    public void getAllCategories(Empty request, StreamObserver<CategoriesGrpc> responseObserver)
    {
        log.info("New request for getting all categories");
        var allCategories = new ArrayList<CategoryReadGrpcDto>();
        categoriesRegistry.findAll().forEach(category -> allCategories.add(category.convertToGrpcReadDto()));
        responseObserver.onNext(CategoriesGrpc.newBuilder().addAllCategories(allCategories).build());
        responseObserver.onCompleted();
    }

    /**
     * Creates a new category in the database
     * @param request contains all information needed
     * @param responseObserver sends back the created category
     */
    @Override
    public void createCategory(CategoryCreationGrpcDto request, StreamObserver<CategoryReadGrpcDto> responseObserver)
    {
        log.info("New request for creating a category");
        var newCategory = new Category();
        newCategory.setDescription(request.getDescription());
        try
        {
            categoriesRegistry.findAll().forEach(category -> {
                if (category.getDescription().equals(newCategory.getDescription()))
                    throw new IllegalArgumentException("Category already exists in the database");
            });
            categoriesRegistry.save(newCategory);
            responseObserver.onNext(categoriesRegistry.findTopByOrderByIdDesc().convertToGrpcReadDto());
            responseObserver.onCompleted();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT)));
        }

    }

    /**
     * Deletes a category from the database
     * @param request GenericMessage contains the categoryId
     * @param responseObserver GenericMessage containing a reply
     */
    @Override
    public void deleteCategory(GenericMessage request, StreamObserver<GenericMessage> responseObserver)
    {
        try
        {
            categoriesRegistry.deleteById(Integer.parseInt(request.getMessage()));
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("Successfully deleted").build());
            responseObserver.onCompleted();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT)));
        }
    }
}
