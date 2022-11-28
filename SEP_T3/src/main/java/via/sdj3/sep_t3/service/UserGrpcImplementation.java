package via.sdj3.sep_t3.service;


import com.google.rpc.Code;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.adapters.MapperImplementation;
import via.sdj3.sep_t3.model.Users;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static via.sdj3.sep_t3.service.GrpcImplementationHelper.generateCustomError;

@GRpcService
@Slf4j
public class UserGrpcImplementation extends sepServiceGrpc.sepServiceImplBase
{
    @Autowired
    UserRegistry userRegistry;

    private MapperImplementation mapper=MapperImplementation.INSTANCE;
    @Override
    public void getAllUsers(Empty request, StreamObserver<AllUsers> responseObserver)
    {
        log.info("new request for getting all users");
        List<UserReadGrpcDTO> allUsers=new ArrayList<>();

        for (Users user: userRegistry.findAll())
        {
            var temp=user.convertToUserReadGrpcDto();
            allUsers.add(temp);
        }
        var ret = AllUsers.newBuilder().addAllUsers(allUsers).build();
        responseObserver.onNext(ret);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(UserCreationGrpcDto request, StreamObserver<UserReadGrpcDTO> responseObserver)
    {
        log.info("new request for creating a new user with credentials "+request.toString());
        var newUser= mapper.convertFromCreateUserGrpcDto(request);
        newUser.setRegisteredOn(LocalDateTime.now());
        newUser.setLastSeen(LocalDateTime.now());
        try
        {
            userRegistry.save(newUser);
            log.info("saved new user with username: "+request.getUsername());
            responseObserver.onNext(newUser.convertToUserReadGrpcDto());
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            var status=generateCustomError(e.getMessage(),Code.INVALID_ARGUMENT);
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
        responseObserver.onCompleted();    }

    @Override
    public void getUserById(GenericMessage request, StreamObserver<UserReadGrpcDTO> responseObserver)
    {
        log.info("new request for getting user by id:"+ request.getMessage());
        var temp= userRegistry.findById(Integer.parseInt(request.getMessage()));
        if (temp.isPresent())
        {
            responseObserver.onNext(temp.get().convertToUserReadGrpcDto());
            responseObserver.onCompleted();
            return;
        }
        //if we got this far we know that there is no user with that ID
        var status=generateCustomError("No user found with this id: "+request.getMessage(),Code.INVALID_ARGUMENT);
        responseObserver.onError(StatusProto.toStatusRuntimeException(status));
    }

    @Override
    public void validateLogin(LoginCredentials request, StreamObserver<UserReadGrpcDTO> responseObserver)
    {
        log.info("Someone trying to login with the following information: "
                +request.getUsername()+" : "+request.getPassword());
        var user= userRegistry.findByUsernameAndUserPass(
                request.getUsername(),
                request.getPassword());

        if (user.isPresent())
        {
            userRegistry.updateLastSeenById(LocalDateTime.now(),user.get().getId());
            responseObserver.onNext(user.get().convertToUserReadGrpcDto());
            responseObserver.onCompleted();
            log.info("Login successful!");
            return;
        }
        log.error("Username or password wrong for user: "+request.getUsername());
        var status=generateCustomError("Username or password wrong for user: "+request.getUsername(),Code.INVALID_ARGUMENT);
        responseObserver.onError(StatusProto.toStatusRuntimeException(status));
    }



    @Override
    public void deleteById(GenericMessage request, StreamObserver<GenericMessage> responseObserver)
    {
        try
        {
            int id = Integer.parseInt(request.getMessage());
            userRegistry.deleteById(id);
            log.info("User with id "+id+ " was deleted from the system");
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("User with id "+id+ " was deleted from the system!").build());
            responseObserver.onCompleted();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            var status = generateCustomError(request.getMessage(),Code.INVALID_ARGUMENT);
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }
}