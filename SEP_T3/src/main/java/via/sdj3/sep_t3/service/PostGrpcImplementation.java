package via.sdj3.sep_t3.service;

import com.google.rpc.Code;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.adapters.MapperImplementation;
import via.sdj3.sep_t3.model.Post;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.CategoriesRegistry;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static via.sdj3.sep_t3.service.GrpcImplementationHelper.generateCustomError;

@Slf4j
@GRpcService
public class PostGrpcImplementation extends postServiceGrpc.postServiceImplBase
{
    private final PostRegistry postRegistry;
    private final UserRegistry userRegistry;
    private final CategoriesRegistry categoriesRegistry;
    private final MapperImplementation mapper = MapperImplementation.INSTANCE;

    @Autowired
    public PostGrpcImplementation(PostRegistry postRegistry, UserRegistry userRegistry, CategoriesRegistry categoriesRegistry)
    {
        this.postRegistry = postRegistry;
        this.userRegistry = userRegistry;
        this.categoriesRegistry = categoriesRegistry;
    }

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
     * @implNote after .onError dont call anything else
     */
    @Override
    public void createPost(PostCreationGrpcDto request, StreamObserver<PostReadGrpcDto> responseObserver)
    {
        log.info("new request for creating a new post with credentials \n"+request.toString());
        var newPost=mapper.convertFromCreatePostsGrpcDto(request);
        //todo blazor client chooses this from a dropdown list
        //newPost.setCategory(null);//NOT SURE HOW TO HANDLE THIS
        try
        {
            newPost.setCreationDate(LocalDate.now());
            if (categoriesRegistry.findById(request.getCategories()).isEmpty())throw new IllegalArgumentException("");
            if (userRegistry.findById(request.getOwnerId()).isEmpty())throw new IllegalArgumentException("");

            newPost.setCategory(categoriesRegistry.findById(request.getCategories()).get());
            newPost.setSellerid(userRegistry.findById(request.getOwnerId()).get());
            postRegistry.save(newPost);
            log.info("saved post: "+newPost);
            responseObserver.onNext(newPost.convertToPostReadGrpcDto());
            responseObserver.onCompleted();
        }catch (Exception e)
        {
            log.error("Cant create post due to invalid argument"+e.getMessage());
            var status=generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT);
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    /**
     * Fetches a post using an Id
     * @param request contains a message requesting to fetch a post using an Id
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
            postRegistry.updatePostById(description,location,category,pictureUrl,price,id);
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
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("true").build());
            responseObserver.onCompleted();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(),Code.INVALID_ARGUMENT)));
        }
    }
}
