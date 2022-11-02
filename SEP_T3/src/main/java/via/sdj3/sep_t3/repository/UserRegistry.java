package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.User;

public interface UserRegistry extends CrudRepository<User,Integer>
{
}
