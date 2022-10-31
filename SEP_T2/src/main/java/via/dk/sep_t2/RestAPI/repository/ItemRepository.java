package via.dk.sep_t2.RestAPI.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import via.dk.sep_t2.RestAPI.model.Item;
import via.dk.sep_t2.RestAPI.model.Post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository
{
    private static final Map<Integer, Item> itemMap = new HashMap<>();

    static
    {
        initDataSource();
    }

    private static void initDataSource()
    {
        Item item1 = new Item(1,"Motherboard", 35.7, "Used");
        Item item2 = new Item(2,"Processor", 23.7, "Used");
        Item item3 = new Item(3,"Graphics card", 60.0, "Used");
        Item item4 = new Item(4,"Power unit", 19.46, "Broken");
        Item item5 = new Item(5,"Internet card", 85.3, "New");
        Item item6 = new Item(6,"Fan", 22.9, "Used");

        itemMap.put(item1.getId(),item1);
        itemMap.put(item2.getId(),item2);
        itemMap.put(item3.getId(),item3);
        itemMap.put(item4.getId(),item4);
        itemMap.put(item5.getId(),item5);
        itemMap.put(item6.getId(),item6);
    }

    //C
    public Item create(Item item)
    {
        itemMap.put(item.getId(), item);
        return item;
    }
    //R

    public Item read(int id)
    {
        if (!itemMap.containsKey(id)) throw new IllegalArgumentException("Post doesnt exist!");
        return itemMap.get(id);
    }

    //U

    public Item update(int id, Item newItem)

    {
        if (!itemMap.containsKey(id)) throw new IllegalArgumentException("Post doesnt exist!");
        itemMap.replace(id, newItem);
        return newItem;
    }

    //D

    public void delete(int id)
    {
        if (!itemMap.containsKey(id)) throw new IllegalArgumentException("Post doesnt exist!");

        itemMap.remove(id);
    }

    public ArrayList<Item> findAll()
    {
        Collection<Item> items = itemMap.values();
        ArrayList<Item> returns = new ArrayList<>();
        for (Item item : items)
        {
            returns.add(item);
        }
        return returns;
    }
}
