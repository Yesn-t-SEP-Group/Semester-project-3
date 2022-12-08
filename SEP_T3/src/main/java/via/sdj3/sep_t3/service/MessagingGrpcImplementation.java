package via.sdj3.sep_t3.service;

import com.google.rpc.Code;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import via.sdj3.sep_t3.model.Message;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.MessageRegistry;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.util.stream.Collectors;

import static via.sdj3.sep_t3.service.GrpcImplementationHelper.generateCustomError;

@Slf4j
@GRpcService
public class MessagingGrpcImplementation extends privateMessageServiceGrpc.privateMessageServiceImplBase
{
    private final PostRegistry postRegistry;
    private final MessageRegistry messageRegistry;
    private final UserRegistry userRegistry;

    public MessagingGrpcImplementation(MessageRegistry messageRegistry, UserRegistry userRegistry,
                                       PostRegistry postRegistry)
    {
        this.messageRegistry = messageRegistry;
        this.userRegistry = userRegistry;
        this.postRegistry = postRegistry;
    }

    /**
     * Handles a request to create a new message.
     *
     * @param request          the request to create a new message
     * @param responseObserver the stream observer that will receive the response
     */
    @Override
    public void createMessage(CreateDMGrpcDto request, StreamObserver<ReadDMGrpcDto> responseObserver)
    {
        log.info("Received request to create a new message");
        try
        {
            var userFrom = userRegistry.findById(request.getUserFromId()).orElseThrow(() -> new IllegalArgumentException("User with ID " + request.getUserFromId() + " does not exist"));
            var userTo = userRegistry.findById(request.getUserToId()).orElseThrow(() -> new IllegalArgumentException("User with ID " + request.getUserToId() + " does not exist"));
            var post = postRegistry.findById(request.getPostId()).orElseThrow(() -> new IllegalArgumentException("Post with ID " + request.getPostId() + " does not exist"));

            if (userFrom.equals(userTo)) throw new IllegalArgumentException("You cant write a message to yourself");

            var newMessage = new Message();
            newMessage.setUserFrom(userFrom);
            newMessage.setUserTo(userTo);
            newMessage.setMessageText(request.getMessageText());
            newMessage.setPost(post);

            log.info("Saving new message to the registry");
            messageRegistry.save(newMessage);
            responseObserver.onNext(newMessage.convertToGrpc());
            responseObserver.onCompleted();
        } catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT)));
        }
    }


    /**
     * Handles a request to get messages made to a user.
     *
     * @param request          the request to get messages made to a user
     * @param responseObserver the stream observer that will receive the response
     */
    @Override
    public void getMessageMadeToUser(GenericMessage request, StreamObserver<AllMessages> responseObserver)
    {
        log.info("Received request to get messages made to user with ID " + request.getMessage());
        try
        {
            var user = userRegistry.findById(Integer.parseInt(request.getMessage()))
                    .orElseThrow(() -> new IllegalArgumentException("User with ID " + request.getMessage() + " does not exist"));
            log.info("Getting messages made to user with ID " + user.getId());
            var messages = messageRegistry.findByUserTo_Id(user.getId());
            var grpcList = messages.stream()
                    .map(Message::convertToGrpc)
                    .collect(Collectors.toList());
            responseObserver.onNext(AllMessages.newBuilder().addAllMessages(grpcList).build());
            responseObserver.onCompleted();
        } catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT)));
        }
    }
}
