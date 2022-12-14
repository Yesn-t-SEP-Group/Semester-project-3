package via.dk.sep_t2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import via.dk.sep_t2.RabbitMQ.config.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SepT2Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(SepT2Application.class, args);
    }

}
