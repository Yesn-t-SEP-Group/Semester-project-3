package via.dk.sep_t2.RestAPI.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import via.dk.sep_t2.RestAPI.model.Post;
import via.dk.sep_t2.RestAPI.model.User;
import via.dk.sep_t2.RestAPI.repository.ItemRepository;
import via.dk.sep_t2.RestAPI.repository.PostRepository;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class PostController
{
    private final PostRepository repository;

    PostController(PostRepository repository)
    {
        this.repository = repository;
    }

    @RequestMapping(value = "/posts/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Post getUserById(@PathVariable int id)
    {
        return repository.read(id);
    }

    @RequestMapping(value = "/posts",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Post newItem(@RequestBody Post post)
    {
        return repository.create(post);
    }

    @RequestMapping(value = "/posts",
            method = RequestMethod.GET//,
            //produces = {MediaType.APPLICATION_XML_VALUE}
    )
    public ArrayList<Post> all()
    {
        return repository.findAll();
    }


    @RequestMapping(value = "/posts/{id}",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Post replace(@RequestBody Post newPost, int id)
    {
        return repository.update(id, newPost);
    }

    @RequestMapping(value = "/posts/{id}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void delete(@PathVariable int id)
    {
        repository.delete(id);
    }
}
