package via.sdj3.sep_t3.rabbitMQ;
/*
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import via.sdj3.sep_t3.backendModel.BackendUser;
import via.sdj3.sep_t3.config.RabbitMqConfig;
import via.sdj3.sep_t3.model.Users;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;

import java.util.ArrayList;
import java.util.List;

@Component
public class Receiver
{
    @Autowired
    private UserRegistry userRegistry;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = RabbitMqConfig.RPC_MESSAGE_QUEUE)
    public void receiveMessage(Message fromClient)
    {
        try
        {
            String message = new String(fromClient.getBody(), "UTF-8");
            System.out.println("Received <" + message + ">");
            Gson gson = new Gson();

            String[] messageSplit = message.split(";");
            /*

            switch (messageSplit[0])
            {
                case "createNewUser":
                {
                   //BackendUser fromJson = gson.fromJson(messageSplit[1], BackendUser.class);
                    Users users = new Users();
                    String returnMessage = "";
                    users.convertFromBackendUser(fromJson);
                    try
                    {
                        userRegistry.save(users);
                        returnMessage = "success";
                    } catch (Exception e)
                    {
                        returnMessage = e.getMessage();
                    }
                    Message build = MessageBuilder.withBody(returnMessage.getBytes()).build();
                    CorrelationData correlationData = new CorrelationData(fromClient.getMessageProperties().getCorrelationId());
                    rabbitTemplate.sendAndReceive(RabbitMqConfig.RPC_EXCHANGE, RabbitMqConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);

                    break;
                }
                case "getUsers":
                {
                    List<BackendUser> ret = new ArrayList<>();
                    //userRegistry.findAll().forEach(users -> ret.add(users.convertToBackendUser()));

                    String returnMessage = gson.toJson(ret);

                    Message build = MessageBuilder.withBody(returnMessage.getBytes()).build();
                    CorrelationData correlationData = new CorrelationData(fromClient.getMessageProperties().getCorrelationId());
                    rabbitTemplate.sendAndReceive(RabbitMqConfig.RPC_EXCHANGE, RabbitMqConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                    break;
                }
                case "login":
                {
                    BackendUser fromJson = gson.fromJson(messageSplit[1], BackendUser.class);
                    String returnMessage = "";

                    if (userRegistry.findByUsernameAndUserPass(fromJson.getUsername(), fromJson.getPassword()).isPresent())
                        returnMessage = "success";
                    else returnMessage = "something wrong";

                    Message build = MessageBuilder.withBody(returnMessage.getBytes()).build();
                    CorrelationData correlationData = new CorrelationData(fromClient.getMessageProperties().getCorrelationId());
                    rabbitTemplate.sendAndReceive(RabbitMqConfig.RPC_EXCHANGE, RabbitMqConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                    break;
                }
            }


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

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
*/