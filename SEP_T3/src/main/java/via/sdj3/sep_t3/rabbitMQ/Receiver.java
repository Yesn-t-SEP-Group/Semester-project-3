package via.sdj3.sep_t3.rabbitMQ;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import via.sdj3.sep_t3.backendModel.BackendUser;
import via.sdj3.sep_t3.model.User;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

@Component
public class Receiver
{
    @Autowired
    private UserRegistry userRegistry;

    @Autowired
    private PostRegistry postRegistry;

    @RabbitHandler
    public void receiveMessage(byte[] body)
    {
        String message = null;
        try
        {
            message = new String(body, "UTF-8");
            Gson gson = new Gson();

            String[] messageSplit = message.split(";");

            switch (messageSplit[0])
            {
                case"createNewUser":
                {
                    BackendUser fromJson = gson.fromJson(messageSplit[1], BackendUser.class);
                    User user =new User();

                    user.setUsername(fromJson.getUsername());
                    user.setUserpass(fromJson.getPassword());
                    user.setAddress(fromJson.getAddress());
                    user.setFullName(fromJson.getFullName());
                    user.setEmail("test");
                    user.setPhoneNumber(fromJson.getPhoneNo());
                    user.setAddress(fromJson.getAddress());
                    user.setRating(BigDecimal.valueOf(fromJson.getRating()));

                    userRegistry.save(user);

                    break;
                }
            }
            /*
            for (User user : userRegistry.findAll())
            {
                System.out.println(user.getFullName());
            }
            postRegistry.findAll().forEach(post -> System.out.println(post.getDescription()));
            for (User user : userRegistry.findByAddress("Horsens"))
            {
                System.out.println(user.getFullName());
            }
            User test = new User();

            userRegistry.save(test);
             */


        } catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }
        System.out.println("Received <" + message + ">");

    }
}