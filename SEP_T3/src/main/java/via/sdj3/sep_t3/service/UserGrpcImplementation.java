package via.sdj3.sep_t3.service;


import com.google.rpc.Code;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.adapters.MapperImplementation;
import via.sdj3.sep_t3.model.Report;
import via.sdj3.sep_t3.model.User;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.ReportRegistry;
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
    final
    ReportRegistry reportRegistry;
    private final MapperImplementation mapper = MapperImplementation.INSTANCE;

    @Autowired
    public UserGrpcImplementation(UserRegistry userRegistry, ReportRegistry reportRegistry)
    {
        this.userRegistry = userRegistry;
        this.reportRegistry = reportRegistry;
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
        } catch (Exception e)
        {
            log.error(e.getMessage());
            var status = generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT);
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
        responseObserver.onCompleted();
    }

    /**
     * Fetches the user by using an Id
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
     * Deletes a user using an Id
     *
     * @param request          the message containing the Id for deleting an user
     * @param responseObserver contains the message with a confirmation from the server confirming the deletion of the user using an Id
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
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("Successfully updated password").build());

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
    public void updateUserInformation(UserUpdateGrpcDTO request, StreamObserver<GenericMessage> responseObserver)
    {
        try
        {
            if (userRegistry.findById(request.getId()).isEmpty())
                throw new IllegalArgumentException("User not found in the database!");

            var username = request.getUsername();
            var fullName = request.getName();
            var email = request.getEmail();
            var phoneNumber = request.getPhoneNumber();
            var address = request.getAddress();
            userRegistry.updateUserInformation(username, fullName, email, phoneNumber, address, request.getId());
        } catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT)));
        }
    }

    @Override
    public void reportUser(ReportCreationGrpcDto request, StreamObserver<GenericMessage> responseObserver)
    {
        try
        {
            var newReport = new Report();
            if (userRegistry.findById(request.getReportedUserId()).isEmpty())
                throw new IllegalArgumentException("");
            newReport.setReportedUser(userRegistry.findById(request.getReportedUserId()).get());
            newReport.setReason(request.getReason());
            newReport.setReportDate(LocalDateTime.now());
            reportRegistry.save(newReport);
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("Report created!").build());
            responseObserver.onCompleted();
        } catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT)));
        }
    }

    /**
     * It requests all reports from the server
     * @param request it sends an empty requests towards the server
     * @param responseObserver it will answer with all the reports
     */
    @Override
    public void getAllReports(Empty request, StreamObserver<ReportReadDto> responseObserver)
    {
        log.info("New request for getting all reports");
        reportRegistry.findAll().forEach(report -> responseObserver.onNext(report.convertToGrpcDto()));
        responseObserver.onCompleted();
    }
}