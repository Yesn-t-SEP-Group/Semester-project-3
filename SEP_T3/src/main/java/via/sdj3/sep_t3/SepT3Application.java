package via.sdj3.sep_t3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "via.sdj3.sep_t3.*")
@EntityScan(basePackages="via.dk.sdj3.sep_t3.model")
@EnableJpaRepositories(basePackages = {"via.sdj3.sep_t3.repository*"})
public class SepT3Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(SepT3Application.class, args);
    }

}
