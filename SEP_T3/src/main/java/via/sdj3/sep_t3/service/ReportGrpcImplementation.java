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

@GRpcService
@Slf4j
public class ReportGrpcImplementation extends reportServiceGrpc.reportServiceImplBase
{
    final ReportRegistry reportRegistry;

    final UserRegistry userRegistry;

    @Autowired
    public ReportGrpcImplementation(ReportRegistry reportRegistry,UserRegistry userRegistry)
    {
        this.reportRegistry = reportRegistry;
        this.userRegistry=userRegistry;
    }


    @Override
    public void reportUser(ReportCreationGrpcDto request, StreamObserver<GenericMessage> responseObserver)
    {
        try
        {
            var newReport = new Report();
            if (userRegistry.findById(request.getReportedUserId()).isEmpty())
                throw new IllegalArgumentException("User does not exist");
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
    public void getAllReports(Empty request, StreamObserver<AllReports> responseObserver)
    {
        log.info("New request for getting all reports");
        var grpcReports=new ArrayList<ReportReadDto>();
        reportRegistry.findAll().forEach(report -> grpcReports.add(report.convertToGrpcDto()));
        responseObserver.onNext(AllReports.newBuilder().addAllReport(grpcReports).build());
        responseObserver.onCompleted();
    }
}
