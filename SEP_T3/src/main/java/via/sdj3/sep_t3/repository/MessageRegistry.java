package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Message;

import java.util.List;


/**
 * This code defines an interface called MessageRegistry that extends the CrudRepository interface.
 */
public interface MessageRegistry extends CrudRepository<Message, Integer>
{
    /**
     * Finds all messages made to a user
     *
     * @param id userId
     * @return List of messages
     */
    List<Message> findByUserTo_Id(Integer id);
}
