package via.dk.sep_t2.RestAPI.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import via.dk.sep_t2.RestAPI.model.Item;
import via.dk.sep_t2.RestAPI.model.User;
import via.dk.sep_t2.RestAPI.repository.PostRepository;
import via.dk.sep_t2.RestAPI.repository.UserRepository;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class UserController
{
    private final UserRepository repository;

    UserController(UserRepository repository)
    {
        this.repository = repository;
    }

    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User getUserById(@PathVariable int id)
    {
        return repository.read(id);
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public User newItem(@RequestBody User user)
    {
        return repository.create(user);
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.GET//,
            //produces = {MediaType.APPLICATION_XML_VALUE}
    )
    public ArrayList<User> all()
    {
        return repository.findAll();
    }


    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public User replace(@RequestBody User newUser, int id)
    {
        return repository.update(id, newUser);
    }

    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void delete(@PathVariable int id)
    {
        repository.delete(id);
    }
}

