package via.dk.sep_t2.RabbitMQ.sender;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import via.dk.sep_t2.RestAPI.model.User;

import java.util.Scanner;

public class Sender
{
    private final static String QUEUE_NAME = "database_queue";
    private final static String HOST = "localhost";
    public static void main(String[] args) throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        Scanner input = new Scanner(System.in);
        Gson gson = new Gson();

        try (Connection conn = factory.newConnection();
             Channel channel = conn.createChannel())
        {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            while (true)
            {
                System.out.println("Enter [Username]:");
                String username = input.nextLine();
                System.out.println("Enter [Password]:");
                String password = input.nextLine();
                System.out.println("Enter [Fullname]:");
                String fullName = input.nextLine();
                System.out.println("Enter [Email]:");
                String email = input.nextLine();
                System.out.println("Enter [Phone No.] (must start with +45):");
                String phoneNumber = input.nextLine();
                System.out.println("Enter [Address]:");
                String address = input.nextLine();

                User user = new User(1,username,password,fullName,email,phoneNumber,address);

                String message = "createNewUser;" + gson.toJson(user);

                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("SENT >> " + message);
            }
        }
    }
}
