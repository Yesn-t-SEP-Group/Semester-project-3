package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Category;
import via.sdj3.sep_t3.model.Rating;

/**
 * This code defines an interface called CategoriesRegistry that extends the CrudRepository interface.
 */
public interface CategoriesRegistry extends CrudRepository<Category,Integer>
{
    /**
     * Finds the latest added category
     *
     * @return the category
     */
    Category findTopByOrderByIdDesc();
}
