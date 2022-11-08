package via.dk.sep_t2.RabbitMQ.reciever;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

    private Gson gson = new Gson();

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getAllUsers(String message)
    {
        Message newMessage = MessageBuilder.withBody(message.getBytes()).build();

        Message result = rabbitTemplate.sendAndReceive(RabbitMqConfig.RPC_EXCHANGE, RabbitMqConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null)
        {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();

            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            //if (msgId.equals(correlationId)) {
            try
            {
                response = new String(result.getBody(), "UTF-8");
                Type listType = new TypeToken<ArrayList<User>>()
                {
                }.getType();
                ArrayList<User> temp = gson.fromJson(response, listType);
                temp.forEach(System.out::println);
            } catch (UnsupportedEncodingException e)
            {
                throw new RuntimeException(e);
            }
            //}
        }
        return response;
    }

    @PostMapping("/user")
    public String createNewUser(@RequestParam String message) throws UnsupportedEncodingException
    {

        User user = new User(0, "Bob", "1234", "Bob the builder", "asd", "+452", "Horsens");

        String msg = "login;" + gson.toJson(user, User.class);
        return getResponse(msg);

    }
    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password) throws UnsupportedEncodingException
    {
        User user = new User(0, userName, password, null, null, null, null);
        String msg = "login;" + gson.toJson(user, User.class);
        String result= getResponse(msg);
        if (result.equals("success"))
        {
            System.out.println("logged in");
            //todo create JWT token
        }
        else
        {
            System.out.println("somethign wrong");
        }
        return result;
    }


    private String getResponse(String msg) throws UnsupportedEncodingException
    {
        Message newMessage = MessageBuilder.withBody(msg.getBytes()).build();
        Message result = rabbitTemplate.sendAndReceive(RabbitMqConfig.RPC_EXCHANGE, RabbitMqConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null)
        {
            response = new String(result.getBody(), "UTF-8");
        }
        return response;
    }
}