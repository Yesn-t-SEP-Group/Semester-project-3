package via.sdj3.sep_t3.rabbitMQ;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import via.sdj3.sep_t3.model.Post;
import via.sdj3.sep_t3.model.User;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

@Component
public class Receiver {
    @Autowired
    private UserRegistry userRegistry;

    @Autowired
    private PostRegistry postRegistry;
    @RabbitHandler
    public void receiveMessage(byte[] body) {
        String message= null;
        try
        {
            message = new String(body,"UTF-8");
            for(User user:userRegistry.findAll())
            {
                System.out.println(user.getFullName());
            }
            postRegistry.findAll().forEach(post -> System.out.println(post.getDescription()));
            for(User user:userRegistry.findByAddress("Horsens"))
            {
                System.out.println(user.getFullName());
            }

        } catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }
        System.out.println("Received <" + message+ ">");

    }
}