package via.sdj3.sep_t3.service;

import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.model.Posts;
import via.sdj3.sep_t3.model.Users;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@GRpcService
public class PostGrpcImplementation extends postServiceGrpc.postServiceImplBase
{
    @Autowired
    private PostRegistry postRegistry;
    @Autowired
    private UserRegistry userRegistry;

    @Override
    public void getAllPosts(Empty request, StreamObserver<AllPosts> responseObserver)
    {
        System.out.println("new request for getting all posts");
        List<PostReadGrpcDto> allPosts =new ArrayList<>();

        for (Posts post: postRegistry.findAll())
        {
            System.out.println(post);
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
        System.out.println("new request for creating a new post with credentials "+request.toString());
        var newPost=new Posts();
        newPost.setId(request.getSellerId());
        newPost.setCreationDate(LocalDate.now());
        newPost.setDescription(request.getDescription());
        newPost.setLocation(request.getLocation());
        newPost.setCategory(null);//NOT SURE HOW TO HANDLE THIS
        newPost.setSellerid(userRegistry.findById(request.getSellerId()).get());
        newPost.setPictureUrl(request.getPicture());
        newPost.setPrice(request.getPrice());
        try
        {
            postRegistry.save(newPost);
            System.out.println("saved new user with username: "+request.toString());
            responseObserver.onNext(newPost.convertToPostReadGrpcDto());
        }catch (Exception e)
        {
            var status=generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT);
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void getPostById(GenericMessage request, StreamObserver<PostReadGrpcDto> responseObserver)
    {
        System.out.println("new request for getting post by id:"+ request.getMessage());
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
