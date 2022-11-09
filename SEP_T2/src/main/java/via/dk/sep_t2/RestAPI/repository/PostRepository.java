package via.dk.sep_t2.RestAPI.repository;

import org.springframework.stereotype.Repository;
import via.dk.sep_t2.RestAPI.model.Item;
import via.dk.sep_t2.RestAPI.model.Post;
import via.dk.sep_t2.RestAPI.model.SearchPostParameters;

import java.util.*;

@Repository
public class PostRepository 
{
    private static final Map<Integer, Post> postMap = new HashMap<>();
    private static ItemRepository itemRepository = new ItemRepository();
    private static UserRepository userRepository = new UserRepository();

    static
    {
        initDataSource();
    }

    private static void initDataSource()
    {
        ArrayList<Item> list1 = new ArrayList();
        list1.add(itemRepository.read(1));
        list1.add(itemRepository.read(2));
        list1.add(itemRepository.read(3));


        ArrayList<Item> list2 = new ArrayList();
        list1.add(itemRepository.read(4));
        list1.add(itemRepository.read(5));
        list1.add(itemRepository.read(6));


        Post post1 = new Post(1,1, list1, "Changed some stuff in my PC, components in good shape.");
        Post post2 = new Post(2,2, list2, "Stuff I found");
    }

    //C
    public Post create(Post post)
    {
        postMap.put(post.getId(), post);
        return post;
    }
    //R

    public Post read(int id)
    {
        if (!postMap.containsKey(id)) throw new IllegalArgumentException("Post doesnt exist!");
        return postMap.get(id);
    }

    //U

    public Post update(int id, Post newPost)

    {
        if (!postMap.containsKey(id)) throw new IllegalArgumentException("Post doesnt exist!");
        postMap.replace(id, newPost);
        return newPost;
    }

    //D

    public void delete(int id)
    {
        if (!postMap.containsKey(id)) throw new IllegalArgumentException("Post doesnt exist!");

        postMap.remove(id);
    }

    public ArrayList<Post> findAll()
    {
        Collection<Post> posts = postMap.values();
        ArrayList<Post> returns = new ArrayList<>();
        for (Post post : posts)
        {
            returns.add(post);
        }
        return returns;
    }

    public ArrayList<Post> findWithFilters(SearchPostParameters parameters)
    {
        if (parameters.isEmpty())
            return new ArrayList<Post>(postMap.values());

        ArrayList<Post> result = new ArrayList<>();
        for (Post a : postMap.values())
        {
            if (Objects.equals(parameters.getCreatorId(), a.getCreatorId())
                    || a.getTotalPrice() <= parameters.getCheaperThan()
            )
            //add more potential filters here
            {
                result.add(a);
            }
        }
        return result;
    }

}
