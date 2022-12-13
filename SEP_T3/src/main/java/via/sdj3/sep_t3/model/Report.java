package via.sdj3.sep_t3.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import via.sdj3.sep_t3.protobuf.ReportReadGrpcDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * Report entity for JPA.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "reports", schema = "sep3")

public class Report
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_user_id")
    @ToString.Exclude
    private User reportedUser;

    @Column(name = "report_date", nullable = false)
    private LocalDateTime reportDate;

    @Column(name = "reason")
    @Type(type = "org.hibernate.type.TextType")
    private String reason;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Report report = (Report) o;
        return id != null && Objects.equals(id, report.id);
    }

    /***
     * It will convert the report into what gRPC dto uses
     * @return it returns a written value into the dto that matches the java part
     */
    public ReportReadGrpcDto convertToGrpcDto()
    {
        return ReportReadGrpcDto.newBuilder()
                .setReason(reason)
                .setReportDate((int) reportDate.toEpochSecond(ZoneOffset.UTC))
                .setReportedUserId(reportedUser.getId())
                .setReportId(id)
                .build();
    }
}