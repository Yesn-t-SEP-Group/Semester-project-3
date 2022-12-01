package via.sdj3.sep_t3.service;


import com.google.rpc.Code;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.adapters.MapperImplementation;
import via.sdj3.sep_t3.model.User;
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
    final
    UserRegistry userRegistry;
    private final MapperImplementation mapper = MapperImplementation.INSTANCE;

    @Autowired
    public UserGrpcImplementation(UserRegistry userRegistry)
    {
        this.userRegistry = userRegistry;
    }

    /**
     * It requests to get all users from the server
     *
     * @param request it contains an empty request that does not affect the server
     * @param responseObserver it returns all the users and confirms that it did so
     */
    @Override
    public void getAllUsers(Empty request, StreamObserver<AllUsers> responseObserver)
    {
        log.info("new request for getting all users");
        List<UserReadGrpcDTO> allUsers = new ArrayList<>();

        for (User user : userRegistry.findAll())
        {
            var temp = user.convertToUserReadGrpcDto();
            allUsers.add(temp);
        }
        var ret = AllUsers.newBuilder().addAllUsers(allUsers).build();
        responseObserver.onNext(ret);
        responseObserver.onCompleted();
    }

    /**
     * Creates a user
     *
     * @param request          user to be created
     * @param responseObserver gRPC requirement
     */
    @Override
    public void createUser(UserCreationGrpcDto request, StreamObserver<UserReadGrpcDTO> responseObserver)
    {
        log.info("new request for creating a new user with credentials " + request.toString());
        var newUser = mapper.convertFromCreateUserGrpcDto(request);
        newUser.setRegisteredOn(LocalDateTime.now());
        newUser.setLastSeen(LocalDateTime.now());
        try
        {
            userRegistry.save(newUser);
            log.info("saved new user with username: " + request.getUsername());
            responseObserver.onNext(newUser.convertToUserReadGrpcDto());
            responseObserver.onCompleted();
        } catch (Exception e)
        {
            log.error(e.getMessage());
            var status = generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT);
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    /**
     * Fetches the user by using an id
     *
     * @param request          user to be taken/request towards the server
     * @param responseObserver the response from the server on the request
     */
    @Override
    public void getUserById(GenericMessage request, StreamObserver<UserReadGrpcDTO> responseObserver)
    {
        log.info("new request for getting user by id:" + request.getMessage());
        var temp = userRegistry.findById(Integer.parseInt(request.getMessage()));
        if (temp.isPresent())
        {
            responseObserver.onNext(temp.get().convertToUserReadGrpcDto());
            responseObserver.onCompleted();
            return;
        }
        //if we got this far we know that there is no user with that ID
        var status = generateCustomError("No user found with this id: " + request.getMessage(), Code.INVALID_ARGUMENT);
        responseObserver.onError(StatusProto.toStatusRuntimeException(status));
    }

    /**
     * Validates(checks) the user information for the user that is trying to log in
     *
     * @param request          contains the login credentials for verifying the user
     * @param responseObserver answers with a message for confirming the login
     */
    @Override
    public void validateLogin(LoginCredentials request, StreamObserver<UserReadGrpcDTO> responseObserver)
    {
        log.info("Someone trying to login with the following information: "
                + request.getUsername() + " : " + request.getPassword());
        var user = userRegistry.findByUsernameAndUserPass(
                request.getUsername(),
                request.getPassword());

        if (user.isPresent())
        {
            userRegistry.updateLastSeenById(LocalDateTime.now(), user.get().getId());
            responseObserver.onNext(user.get().convertToUserReadGrpcDto());
            responseObserver.onCompleted();
            log.info("Login successful!");
            return;
        }
        log.error("Username or password wrong for user: " + request.getUsername());
        var status = generateCustomError("Username or password wrong for user: " + request.getUsername(), Code.INVALID_ARGUMENT);
        responseObserver.onError(StatusProto.toStatusRuntimeException(status));
    }

    /**
     * Deletes a user using an id
     *
     * @param request          the message containing the id for deleting a user
     * @param responseObserver contains the message with a confirmation from the server confirming the deletion of the user using an id
     */
    @Override
    public void deleteById(GenericMessage request, StreamObserver<GenericMessage> responseObserver)
    {
        try
        {
            int id = Integer.parseInt(request.getMessage());
            userRegistry.deleteById(id);
            log.info("User with id " + id + " was deleted from the system");
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("User with id " + id + " was deleted from the system!").build());
            responseObserver.onCompleted();
        } catch (Exception e)
        {
            log.error(e.getMessage());
            var status = generateCustomError(request.getMessage(), Code.INVALID_ARGUMENT);
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    /**
     * updates a user password
     *
     * @param request          requests for the user password value to be modified
     * @param responseObserver returns the confirmation of the value being modified, and executes the modifications
     */
    @Override
    public void updatePassword(PasswordUpdateGrpcDTO request, StreamObserver<GenericMessage> responseObserver)
    {
        try
        {
            //todo better error messages
            if (userRegistry.findById(request.getId()).isEmpty())
                throw new IllegalArgumentException("No user found in database!");
            var user = userRegistry.findById(request.getId()).get();
            if (request.getNewPassword().equals(user.getUserPass()))
                throw new IllegalArgumentException("New pass cant be same as old pass");
            userRegistry.updateUserPassById(request.getNewPassword(), user.getId());
            log.info(user.getUsername() +" changed their password");
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("Successfully updated password").build());
            responseObserver.onCompleted();

        } catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT)));
        }
    }

    /**
     * It will modify the user personal information
     *
     * @param request          it requests for the user personal information to be modified with new values
     * @param responseObserver it confirms the modified new values and executes the modifications
     */
    @Override
    public void updateUserInformation(UserUpdateGrpcDTO request, StreamObserver<UserReadGrpcDTO> responseObserver)
    {
        try
        {
            var id =request.getId();
            if (userRegistry.findById(id).isEmpty())
                throw new IllegalArgumentException("User not found in the database!");
            var username = request.getUsername();
            var fullName = request.getName();
            var email = request.getEmail();
            var phoneNumber = request.getPhoneNumber();
            var address = request.getAddress();

            var role=request.getRole();
            if (!role.equals(userRegistry.findById(id).get().getRole()))
                log.warn(username + " changed to role to: " + role);
            userRegistry.updateUserInformation(username, fullName, email, phoneNumber, address,role, id);
            log.info(username+" updated with new information");

            responseObserver.onNext(userRegistry.findById(id).get().convertToUserReadGrpcDto());
            responseObserver.onCompleted();
        } catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT)));
        }
    }
}