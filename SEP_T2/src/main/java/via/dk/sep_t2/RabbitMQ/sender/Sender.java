package via.dk.sep_t2.RabbitMQ.sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

public class Sender
{
    private final static String QUEUE_NAME = "database_queue";
    private final static String HOST = "localhost";
    public static void main(String[] args) throws Exception
    {
        String[] viaProducts = {"Cappuccino Cafe Latte", "Deep fried Hawai Vegan Pizza"};
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        Scanner input = new Scanner(System.in);

        try (Connection conn = factory.newConnection();
             Channel channel = conn.createChannel())
        {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            while (true)
            {
                System.out.println("Type message:");
                String message = input.nextLine();

                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                if (message.equals("CLOSE"))
                {
                    break;
                }
                System.out.println("SENT >> " + message);
            }
        }
        System.out.println("CONNECTION TERMINATED");
    }
}
