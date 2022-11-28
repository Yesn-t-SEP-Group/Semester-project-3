package via.sdj3.sep_t3.service;

import com.google.rpc.Code;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;

import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.adapters.MapperImplementation;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.RatingsRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

import static via.sdj3.sep_t3.service.GrpcImplementationHelper.generateCustomError;

/**
 * implementation of the gRPC communication for ratings
 */
@GRpcService
@Slf4j
public class RatingGrpcImplementation extends ratingServiceGrpc.ratingServiceImplBase
{
    @Autowired
    private UserRegistry userRegistry;
    @Autowired
    private RatingsRegistry ratingsRegistry;


    /**
     * It fetches all the ratings
     * @param request contains an empty request
     * @param responseObserver contains a response from the server confirming getting the data
     */
    @Override
    public void getAllRatings(Empty request, StreamObserver<RatingReadGrpcDto> responseObserver)
    {
        super.getAllRatings(request, responseObserver);
    }

    /**
     * It creates a new rating
     * @param request contains a request message for creating a rating
     * @param responseObserver contains an answer with the rating read
     */
    @Override
    public void createRating(RatingCreationGrpcDto request, StreamObserver<RatingReadGrpcDto> responseObserver)
    {
        try
        {
            //todo
            log.info("");
            var newRating= MapperImplementation.INSTANCE.convertFromCreateRatingGrpcDto(request);
            newRating.setUserFrom(userRegistry.findById(request.getUserFromId()).get());
            newRating.setUserTo(userRegistry.findById(request.getUserToId()).get());
            ratingsRegistry.save(newRating);

            //todo implement convert
            responseObserver.onNext(ratingsRegistry.findTopByOrderByIdDesc().convertToRatingReadGrpcDto());
            responseObserver.onCompleted();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(),Code.INVALID_ARGUMENT)));
        }
    }

    /**
     * Fetches a rating using an Id
     * @param request a message containing the Id for requesting the rating
     * @param responseObserver answer from the server with a message containing the rating using an Id
     */
    @Override
    public void getRatingById(GenericMessage request, StreamObserver<RatingReadGrpcDto> responseObserver)
    {
        log.info("");
        try
        {
            responseObserver.onNext(ratingsRegistry.findById(Integer.parseInt(request.getMessage())).get().convertToRatingReadGrpcDto());
            responseObserver.onCompleted();
        }
        catch (Exception e)
        {
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(),Code.INVALID_ARGUMENT)));
        }
    }

    /**
     * It gets all the ratings made to a user
     * @param request contains a message requesting to fetch all ratings contained by a user
     * @param responseObserver contains a message returning all the ratings contained by a user
     */
    @Override
    public void getAllRatingsMadeToUser(GenericMessage request, StreamObserver<RatingReadGrpcDto> responseObserver)
    {
        try
        {
            var id=Integer.parseInt(request.getMessage());
            //todo
            if (userRegistry.findById(id).isEmpty())throw new IllegalArgumentException("shit broke");
            var ratings=ratingsRegistry.findByUserTo_Id(id);
            ratings.forEach(rating -> responseObserver.onNext(rating.convertToRatingReadGrpcDto()));
            responseObserver.onCompleted();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(),Code.INVALID_ARGUMENT)));
        }
    }

    /**
     * deletes a rating
     * @param request contains a message requesting the deletion of a rating contained by a user
     * @param responseObserver contains a message returning the confirmation of deletion of a rating contained by a user
     */
    @Override
    public void deleteRating(GenericMessage request, StreamObserver<GenericMessage> responseObserver)
    {
        try
        {
            var requestId=Integer.parseInt(request.getMessage());
            log.info("Request for deleting rating with id: "+requestId);

            ratingsRegistry.deleteById(requestId);
            log.info("Rating with id: "+requestId+" deleted!");
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("Rating with id: "+requestId+" deleted!").build());
            responseObserver.onCompleted();
        }catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(),Code.INVALID_ARGUMENT)));
        }
    }
}
