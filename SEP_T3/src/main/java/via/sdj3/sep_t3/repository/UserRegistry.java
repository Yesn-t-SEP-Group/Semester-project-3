package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.User;

import java.util.List;

public interface UserRegistry extends CrudRepository<User,Integer>
{
    List<User> findByAddress(String address);

}
