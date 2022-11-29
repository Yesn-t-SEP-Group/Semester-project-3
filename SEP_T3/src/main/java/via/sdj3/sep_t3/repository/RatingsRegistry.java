package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Rating;

import java.util.List;

/**
 * it contains the rating registry and extends the crud
 */
public interface RatingsRegistry extends CrudRepository<Rating,Integer>
{
    /**
     * Finds all ratings  made to a given user
     * @param id userId that we query
     * @return List of ratings made to that user
     */
    List<Rating> findByUserTo_Id(Integer id);
    Rating findTopByOrderByIdDesc();
}
