package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Item;

public interface ItemRegistry extends CrudRepository<Item,Integer>
{
    //write sql specific methods here
}
