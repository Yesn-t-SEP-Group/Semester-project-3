package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserRegistry extends CrudRepository<Users,Integer>
{
    List<Users> findByAddress(String address);

    Optional<Users> findByUsernameAndUserPass(String username, String userPass);


}
