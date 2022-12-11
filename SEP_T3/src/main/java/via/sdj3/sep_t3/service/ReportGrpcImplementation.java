package via.sdj3.sep_t3.service;

import com.google.rpc.Code;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.model.Report;
import via.sdj3.sep_t3.protobuf.*;
import via.sdj3.sep_t3.repository.ReportRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static via.sdj3.sep_t3.service.GrpcImplementationHelper.generateCustomError;

/**
 * implementation of the gRPC communication for reports
 */
@GRpcService
@Slf4j
public class ReportGrpcImplementation extends reportServiceGrpc.reportServiceImplBase
{
    private final ReportRegistry reportRegistry;

    private final UserRegistry userRegistry;

    /**
     * Autowired constructor for dependency injection
     *
     * @param reportRegistry the report registry
     * @param userRegistry   the user registry
     */
    @Autowired
    public ReportGrpcImplementation(ReportRegistry reportRegistry,UserRegistry userRegistry)
    {
        this.reportRegistry = reportRegistry;
        this.userRegistry=userRegistry;
    }

    /**
     * Creates a report in the database
     * @param request contains information for the report
     * @param responseObserver sends back the created report
     */
    @Override
    public void reportUser(ReportCreationGrpcDto request, StreamObserver<ReportReadGrpcDto> responseObserver)
    {
        log.info("Reporting user with id: "+request.getReportedUserId());
        try
        {
            var newReport = new Report();
            if (userRegistry.findById(request.getReportedUserId()).isEmpty())
                throw new IllegalArgumentException("User does not exist");
            newReport.setReportedUser(userRegistry.findById(request.getReportedUserId()).get());
            newReport.setReason(request.getReason());
            newReport.setReportDate(LocalDateTime.now());
            reportRegistry.save(newReport);
            responseObserver.onNext(reportRegistry.findTopByOrderByIdDesc().convertToGrpcDto());
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
    public void getAllReports(Empty request, StreamObserver<AllReports> responseObserver)
    {
        log.info("New request for getting all reports");
        var grpcReports=new ArrayList<ReportReadGrpcDto>();
        reportRegistry.findAll().forEach(report -> grpcReports.add(report.convertToGrpcDto()));
        responseObserver.onNext(AllReports.newBuilder().addAllReport(grpcReports).build());
        responseObserver.onCompleted();
    }

    /**
     * Gets all the report made to a specific user
     * @param request contains the userId
     * @param responseObserver contians all the reports
     */
    @Override
    public void getALlReportsMadeToUser(GenericMessage request, StreamObserver<AllReports> responseObserver)
    {
        var id = Integer.parseInt(request.getMessage());
        log.info("Getting all reports made to user: "+id);
        try
        {
            if (userRegistry.findById(id).isEmpty())
                throw new IllegalArgumentException("User does not exist");
        }
        catch (IllegalArgumentException e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(),Code.INVALID_ARGUMENT)));
            return;
        }
        var convertedToGrpc=new ArrayList<ReportReadGrpcDto>();
        reportRegistry.findByReportedUser_Id(id).forEach(report -> convertedToGrpc.add(report.convertToGrpcDto()));

        responseObserver.onNext(AllReports.newBuilder().addAllReport(convertedToGrpc).build());
        responseObserver.onCompleted();
    }

    /**
     * Deletes a report from the database
     * @param request contains the reportId
     * @param responseObserver sends back a confirmation
     */
    @Override
    public void deleteReport(GenericMessage request, StreamObserver<GenericMessage> responseObserver)
    {
        log.info("");
        try
        {
            reportRegistry.deleteById(Integer.parseInt(request.getMessage()));
            responseObserver.onNext(GenericMessage.newBuilder().setMessage("Report deleted").build());
            responseObserver.onCompleted();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            responseObserver.onError(StatusProto.toStatusRuntimeException(generateCustomError(e.getMessage(),Code.INVALID_ARGUMENT)));
        }
    }
}
