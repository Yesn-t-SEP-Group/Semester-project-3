package via.sdj3.sep_t3.service;


import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.model.Users;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.time.LocalTime;
import java.time.ZoneOffset;
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
            User temp=User.newBuilder()
                    .setAddress(user.getAddress())
                    .setEmail(user.getEmail())
                    .setFullName(user.getFullName())
                    .setId(user.getId())
                    .setUsername(user.getUsername())
                    .setUserPass(user.getUserPass())
                    .setPhoneNumber(user.getPhoneNumber())
                    .setLastSeen((int) user.getLastSeen().toEpochSecond(LocalTime.NOON, ZoneOffset.MIN))
                    .setRegisteredOn((int) user.getRegisteredOn().toEpochSecond(LocalTime.NOON, ZoneOffset.MIN))
                    .build();

            userGrpc.add(temp);

        }

        UsersGrpc usersGrpc = UsersGrpc.newBuilder().addAllUsers(userGrpc).build();
        responseObserver.onNext(usersGrpc);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(UserDTO request, StreamObserver<GenericReply> responseObserver)
    {
        System.out.println("new request for creating a new user with credentials "+request.toString());
        Users newUser=new Users();
        newUser.setUsername(request.getUsername());
        newUser.setUserPass(request.getUserPass());
        newUser.setEmail(request.getEmail());
        newUser.setAddress(request.getAddress());
        newUser.setPhoneNumber(request.getPhoneNumber());
        try
        {
            userRegistry.save(newUser);
            System.out.println("saved new user with username: "+newUser.getUsername());
            responseObserver.onNext(GenericReply.newBuilder().setMessage("success").build());
            responseObserver.onCompleted();
        }
        catch (Exception e)
        {
            responseObserver.onNext(GenericReply.newBuilder().setMessage("error").build());
            responseObserver.onError(e);
        }


    }
}

