package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Post;

public interface PostRegistry extends CrudRepository<Post,Integer>
{
    //write sql specific methods here
}
