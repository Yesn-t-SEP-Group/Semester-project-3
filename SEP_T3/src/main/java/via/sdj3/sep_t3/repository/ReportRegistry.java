package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Rating;
import via.sdj3.sep_t3.model.Report;

import java.util.List;

/***
 * This code defines an interface called ReportRegistry that extends the CrudRepository interface.
 */
public interface ReportRegistry extends CrudRepository<Report,Integer>
{
    /***
     * the method is used to find users
     * @param id it requests using an id
     * @return it returns a list by id
     */
    List<Report> findByReportedUser_Id(Integer id);

    /**
     * Finds the latest report added
     *
     * @return the report
     */
    Report findTopByOrderByIdDesc();

}
