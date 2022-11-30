package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Report;

import java.util.List;

public interface ReportRegistry extends CrudRepository<Report,Integer>
{
    List<Report> findByReportedUser_Id(Integer id);
}
