package via.sdj3.sep_t3.service;

import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.adapters.MapperImplementation;
import via.sdj3.sep_t3.model.Posts;
import via.sdj3.sep_t3.model.Users;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.CategoriesRegistry;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@GRpcService
public class PostGrpcImplementation extends postServiceGrpc.postServiceImplBase
{
    @Autowired
    private PostRegistry postRegistry;
    @Autowired
    private UserRegistry userRegistry;

    @Autowired
    private CategoriesRegistry categoriesRegistry;

    private MapperImplementation mapper=MapperImplementation.INSTANCE;

    @Override
    public void getAllPosts(Empty request, StreamObserver<AllPosts> responseObserver)
    {
        log.info("new request for getting all posts");
        List<PostReadGrpcDto> allPosts =new ArrayList<>();

        for (Posts post: postRegistry.findAll())
        {
            var temp=post.convertToPostReadGrpcDto();
            allPosts.add(temp);
        }
        var ret = AllPosts.newBuilder().addAllPost(allPosts).build();
        responseObserver.onNext(ret);
        responseObserver.onCompleted();
    }

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
            newPost.setCategory(categoriesRegistry.findById(request.getCategories()).get());
         newPost.setSellerid(userRegistry.findById(request.getSellerId()).get());
            postRegistry.save(newPost);
            log.info("saved post: "+newPost);
            responseObserver.onNext(newPost.convertToPostReadGrpcDto());
            responseObserver.onCompleted();
        }catch (Exception e)
        {
            log.error(e.getMessage());
            var status=generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT);
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

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
        var status=generateCustomError("No post found with this id: "+request.getMessage(),Code.INVALID_ARGUMENT);
        responseObserver.onError(StatusProto.toStatusRuntimeException(status));
    }

    public Status generateCustomError(String message, Code code)
    {
        return Status.newBuilder()
                .setCode(code.getNumber())
                .setMessage(message)
                .build();
    }
}
