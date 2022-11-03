package via.dk.sep_t2.RabbitMQ.reciever;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Reciever
{
    private final static String QUEUE_NAME = "database_queue";
    private final static String HOST = "localhost";
    public static void main(String[] args) throws IOException, TimeoutException
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" RECIEVED >> " + message);
                if (message.equals("CLOSE"))
                {
                    connection.close();
                }

            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
