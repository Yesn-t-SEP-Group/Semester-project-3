package via.sdj3.sep_t3.service;


import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.model.Users;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@GRpcService
public class GrpcImplementation extends sepServiceGrpc.sepServiceImplBase
{
    @Autowired
    UserRegistry userRegistry;

    @Override
    public void getAllUsers(Empty request, StreamObserver<UsersGrpc> responseObserver)
    {
        System.out.println("new request for getting all users");
        List<User>userGrpc=new ArrayList<>();

        for (Users user: userRegistry.findAll())
        {
            System.out.println(user);
            User temp=user.convertToGrpc();
            userGrpc.add(temp);
        }
        UsersGrpc usersGrpc = UsersGrpc.newBuilder().addAllUsers(userGrpc).build();
        responseObserver.onNext(usersGrpc);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(UserDTO request, StreamObserver<User> responseObserver)
    {
        System.out.println("new request for creating a new user with credentials "+request.toString());
        var newUser=new Users();
        newUser.setUsername(request.getUsername());
        newUser.setUserPass(request.getUserPass());
        newUser.setFullName(request.getFullName());
        newUser.setEmail(request.getEmail());
        newUser.setAddress(request.getAddress());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setRegisteredOn(LocalDate.now());
        newUser.setLastSeen(LocalDate.now());
        try
        {
            userRegistry.save(newUser);
            System.out.println("saved new user with username: "+request.getUsername());
            responseObserver.onNext(newUser.convertToGrpc());
        }
        catch (Exception e)
        {
            var status=generateCustomError(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
        responseObserver.onCompleted();
    }

    @Override
    public void validateLogin(loginCredentials request, StreamObserver<GenericMessage> responseObserver)
    {
        System.out.println("Someone trying to login with the following information: "
                +request.getUserName()+" : "+request.getPassword());

        var user= userRegistry.findByUsernameAndUserPass(
                request.getUserName(),
                request.getPassword());

        if (user.isPresent())
        {
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("Success").build());
            responseObserver.onCompleted();
            user.get().setLastSeen(LocalDate.now());
            return;
        }
        var status=generateCustomError("Username or password wrong for user: "+request.getUserName());
        responseObserver.onError(StatusProto.toStatusRuntimeException(status));
    }

    /**
     * Getting a user from the database by supplying the id
     * @param request contains the ID
     * @param responseObserver we use to send back on gRPC
     */
    @Override
    public void getUserById(GenericMessage request, StreamObserver<User> responseObserver)
    {
        System.out.println("new request for getting user by id:"+ request.getMessage());
        var temp= userRegistry.findById(Integer.parseInt(request.getMessage()));
        if (temp.isPresent())
        {
            responseObserver.onNext(temp.get().convertToGrpc());
            responseObserver.onCompleted();
            return;
        }
        //if we got this far we know that there is no user with that ID
        var status=generateCustomError("No user found with this id: "+request.getMessage());
        responseObserver.onError(StatusProto.toStatusRuntimeException(status));
    }
    public Status generateCustomError(String message)
    {
        return Status.newBuilder()
                .setCode(Code.INVALID_ARGUMENT.getNumber())
                .setMessage(message)
                .build();
    }
}

