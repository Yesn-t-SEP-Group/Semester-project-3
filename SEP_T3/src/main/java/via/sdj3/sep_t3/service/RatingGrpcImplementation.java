package via.sdj3.sep_t3.service;

import com.google.rpc.Code;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;

import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.RatingsRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

import static via.sdj3.sep_t3.service.GrpcImplementationHelper.generateCustomError;


@GRpcService
@Slf4j
public class RatingGrpcImplementation extends ratingServiceGrpc.ratingServiceImplBase
{
    @Autowired
    private UserRegistry userRegistry;
    @Autowired
    private RatingsRegistry ratingsRegistry;



    @Override
    public void getAllRatings(Empty request, StreamObserver<RatingReadGrpcDto> responseObserver)
    {
        super.getAllRatings(request, responseObserver);
    }

    @Override
    public void createRating(RatingCreationGrpcDto request, StreamObserver<RatingReadGrpcDto> responseObserver)
    {
      super.createRating(request,responseObserver);
    }

    @Override
    public void getRatingById(GenericMessage request, StreamObserver<RatingReadGrpcDto> responseObserver)
    {
        super.getRatingById(request, responseObserver);
    }

    @Override
    public void getAllRatingsMadeToUser(GenericMessage request, StreamObserver<RatingReadGrpcDto> responseObserver)
    {
        super.getAllRatingsMadeToUser(request, responseObserver);
    }

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
            var status=generateCustomError(e.getMessage(), Code.INVALID_ARGUMENT);
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }

    }
}
