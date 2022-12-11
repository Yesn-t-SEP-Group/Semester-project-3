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
    /**
     * Finds a user by address.
     *
     * @param address the address
     * @return the list
     */
    List<User> findByAddress(String address);

    /**
     * Finds a user by a username and a password
     *
     * @param username the username
     * @param userPass the user pass
     * @return user if it exists
     */
    Optional<User> findByUsernameAndUserPass(String username, String userPass);

    /**
     * Update last seen by id.
     *
     * @param lastSeen the last seen
     * @param id       the id
     */
    @Transactional
    @Modifying
    @Query("update users u set u.lastSeen = ?1 where u.id = ?2")
    void updateLastSeenById(LocalDateTime lastSeen, Integer id);

    /**
     * Update user information.
     *
     * @param username    the username
     * @param fullName    the full name
     * @param email       the email
     * @param phoneNumber the phone number
     * @param address     the address
     * @param role        the role
     * @param id          the id
     */
    @Transactional
    @Modifying
    @Query("""
            update users u set u.username = ?1, u.fullName = ?2, u.email = ?3, u.phoneNumber = ?4, u.address = ?5, u.role = ?6
            where u.id = ?7""")
    void updateUserInformation(String username, String fullName, String email, String phoneNumber, String address,String role, Integer id);

    /**
     * Update user pass by id.
     *
     * @param userPass the user pass
     * @param id       the id
     */
    @Transactional
    @Modifying
    @Query("update users u set u.userPass = ?1 where u.id = ?2")
    void updateUserPassById(String userPass, Integer id);

}
