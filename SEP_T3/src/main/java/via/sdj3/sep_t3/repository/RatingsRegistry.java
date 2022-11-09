package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Ratings;

public interface RatingsRegistry extends CrudRepository<Ratings,Integer>
{
    /**
     * Finds all ratings made to a user
     * @param id
     * @return
     */
    Ratings findByUsersTo_Id(Integer id);

}
