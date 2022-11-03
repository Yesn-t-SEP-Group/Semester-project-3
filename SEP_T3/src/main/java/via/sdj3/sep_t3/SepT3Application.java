package via.sdj3.sep_t3;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import via.sdj3.sep_t3.rabbitMQ.Receiver;

@SpringBootApplication
@ComponentScan(basePackages = "via.sdj3.sep_t3.*")
@EntityScan(basePackages="via.sdj3.sep_t3.model*")
@EnableJpaRepositories(basePackages = {"via.sdj3.sep_t3.repository*"})
public class SepT3Application
{

    static final String TOPIC_EXCHANGE_NAME = "SEP3-exchange";
    static final String QUEUE_NAME="database_queue";

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        //todo change this
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
    public static void main(String[] args)
    {
        SpringApplication.run(SepT3Application.class, args);
    }

}
