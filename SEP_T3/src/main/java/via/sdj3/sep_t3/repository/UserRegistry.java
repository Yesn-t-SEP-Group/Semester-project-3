package via.sdj3.sep_t3.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import via.sdj3.sep_t3.model.Users;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRegistry extends CrudRepository<Users,Integer>
{
    List<Users> findByAddress(String address);

    Optional<Users> findByUsernameAndUserPass(String username, String userPass);

    @Transactional
    @Modifying
    @Query("update users u set u.lastSeen = ?1 where u.id = ?2")
    int updateLastSeenById(LocalDate lastSeen, Integer id);




}
