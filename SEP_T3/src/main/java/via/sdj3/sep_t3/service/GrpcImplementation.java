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
        Users newUser=new Users();
        newUser.setUsername(request.getUsername());
        newUser.setUserPass(request.getUserPass());
        newUser.setEmail(request.getEmail());
        newUser.setAddress(request.getAddress());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setRegisteredOn(LocalDate.now());
        newUser.setLastSeen(LocalDate.now());
        try
        {
            userRegistry.save(newUser);
            System.out.println("saved new user with username: "+newUser.getUsername());
            responseObserver.onNext(newUser.convertToGrpc());
        }
        catch (Exception e)
        {
            Status status= Status.newBuilder()
                    .setCode(Code.INVALID_ARGUMENT.getNumber())
                    .setMessage(e.getMessage())
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
        responseObserver.onCompleted();
    }
}

