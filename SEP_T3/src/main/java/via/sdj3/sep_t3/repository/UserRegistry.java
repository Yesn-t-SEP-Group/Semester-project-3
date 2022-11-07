package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRegistry extends CrudRepository<User,Integer>
{
    List<User> findByAddress(String address);

    Optional<User> findByUsernameAndUserpass(String username, String userpass);

}
