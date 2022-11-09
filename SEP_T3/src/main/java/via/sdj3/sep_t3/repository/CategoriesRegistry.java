package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Categories;

public interface CategoriesRegistry extends CrudRepository<Categories,Integer>
{
    //write sql specific methods here
}
