package via.sdj3.sep_t3.rabbitMQ;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");

    }
}