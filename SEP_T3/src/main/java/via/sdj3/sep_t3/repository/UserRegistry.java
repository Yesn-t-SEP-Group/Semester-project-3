package via.sdj3.sep_t3.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import via.sdj3.sep_t3.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * It contains the user registry and extends the crud
 */
public interface UserRegistry extends CrudRepository<User,Integer>
{
    List<User> findByAddress(String address);

    Optional<User> findByUsernameAndUserPass(String username, String userPass);

    @Transactional
    @Modifying
    @Query("update users u set u.lastSeen = ?1 where u.id = ?2")
    void updateLastSeenById(LocalDateTime lastSeen, Integer id);

    @Transactional
    @Modifying
    @Query("""
            update users u set u.username = ?1, u.fullName = ?2, u.email = ?3, u.phoneNumber = ?4, u.address = ?5
            where u.id = ?6""")
    void updateUserInformation(String username, String fullName, String email, String phoneNumber, String address, Integer id);

    @Transactional
    @Modifying
    @Query("update users u set u.userPass = ?1 where u.id = ?2")
    int updateUserPassById(String userPass, Integer id);








}
