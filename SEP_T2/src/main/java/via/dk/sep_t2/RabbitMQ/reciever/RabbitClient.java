package via.dk.sep_t2.RabbitMQ.reciever;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import via.dk.sep_t2.RabbitMQ.config.RabbitMqConfig;
import via.dk.sep_t2.RestAPI.model.User;

import java.io.UnsupportedEncodingException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class RabbitClient
{
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/user")
    public String helloWorld(String message)
    {
        Message newMessage = MessageBuilder.withBody(message.getBytes()).build();

        Message result = rabbitTemplate.sendAndReceive(RabbitMqConfig.RPC_EXCHANGE,RabbitMqConfig.RPC_MESSAGE_QUEUE,newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();

            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            //if (msgId.equals(correlationId)) {
                try
                {
                    response = new String(result.getBody(),"UTF-8");
                    Gson gson=new Gson();
                    Type listType=new TypeToken<ArrayList<User>>(){}.getType();
                    ArrayList<User> temp=gson.fromJson(response,listType);
                    temp.forEach(System.out::println);
                } catch (UnsupportedEncodingException e)
                {
                    throw new RuntimeException(e);
                }
            //}
        }
        return response;
    }
}
