package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Category;
import via.sdj3.sep_t3.model.Rating;

/**
 * It registers categories
 */

/**
 * * It creates the users objects and extends the crud
 */
public interface CategoriesRegistry extends CrudRepository<Category,Integer>
{
    Category findTopByOrderByIdDesc();
}
