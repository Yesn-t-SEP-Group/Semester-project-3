package via.sdj3.sep_t3.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sep_t3.model.Message;

import java.util.List;


public interface MessageRegistry extends CrudRepository<Message, Integer>
{
    /**
     * Fins all messages made to a user
     * @param id userId
     * @return List of messages
     */
    List<Message> findByUserTo_Id(Integer id);
}
