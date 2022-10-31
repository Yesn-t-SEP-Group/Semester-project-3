package via.dk.sep_t2.RestAPI.repository;

import org.springframework.stereotype.Repository;
import via.dk.sep_t2.RestAPI.model.User;

import java.util.*;

@Repository
public class UserRepository
{
    private static final Map<Integer, User> userMap = new HashMap<>();

    static
    {
        initDataSource();
    }

    private static void initDataSource()
    {
        User user1 = new User(1,"Lev","lev", "Levente Szejko", "+45 00 00 00 01", "Address1");
        User user2 = new User(2,"Gabe","gabe", "Eduard-Gabriel Vlad", "+45 00 00 00 02", "Address2");
        User user3 = new User(3,"Marti","marti", "Martin Rozendhal", "+45 00 00 00 03", "Address3");
        User user4 = new User(4,"Kruno","kruno", "Kruno Neric", "+45 00 00 00 04", "Address4");

        userMap.put(user1.getId(),user1);
        userMap.put(user2.getId(),user2);
        userMap.put(user3.getId(),user3);
        userMap.put(user4.getId(),user4);
    }

    //C
    public User create(User user)
    {
        userMap.put(user.getId(), user);
        return user;
    }
    //R

    public User read(int id)
    {
        if (!userMap.containsKey(id)) throw new IllegalArgumentException("User doesnt exist!");
        return userMap.get(id);
    }

    //U

    public User update(int id, User newUser)

    {
        if (!userMap.containsKey(id)) throw new IllegalArgumentException("User doesnt exist!");
        userMap.replace(id, newUser);
        return newUser;
    }

    //D

    public void delete(int id)
    {
        if (!userMap.containsKey(id)) throw new IllegalArgumentException("User doesnt exist!");

        userMap.remove(id);
    }

    public ArrayList<User> findAll()
    {
        Collection<User> users = userMap.values();
        ArrayList<User> returns = new ArrayList<>();
        for (User user : users)
        {
            returns.add(user);
        }
        return returns;
    }
}
