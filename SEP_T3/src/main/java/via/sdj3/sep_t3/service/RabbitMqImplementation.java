package via.sdj3.sep_t3.service;

import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.sep_t3.model.User;
import via.sdj3.sep_t3.repository.ItemRegistry;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;


public class RabbitMqImplementation
{
    @Autowired
    private ItemRegistry itemRegistry;
    @Autowired
    private PostRegistry postRegistry;
    @Autowired
    private UserRegistry userRegistry;

    //we put rabbitmq shit here
    //for now its a grpc demo


    public void getAllUsersDemo()
    {
        for(User user:userRegistry.findAll())
        {
            System.out.println(user);
        }
    }


}
