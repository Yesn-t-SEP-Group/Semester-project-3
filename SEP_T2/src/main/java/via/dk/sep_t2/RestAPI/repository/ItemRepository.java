package via.dk.sep_t2.RestAPI.repository;

import org.springframework.stereotype.Repository;
import via.dk.sep_t2.RestAPI.model.Item;
import via.dk.sep_t2.RestAPI.model.SearchItemParameters;

import java.util.*;

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
        Item item1 = new Item(1,"Micro-ATX motherboard", 35.7, "Used","Motherboard");
        Item item2 = new Item(2,"4core Intel processor", 23.7, "Used", "Processor");
        Item item3 = new Item(3,"Nvidia RTX 3060‑Ti graphics card", 60.0, "Used", "Graphics card");
        Item item4 = new Item(4,"EVGA Supernova 1000 T2 power supply", 19.46, "Broken", "Power supply");
        Item item5 = new Item(5,"TP-Link WiFi 6 AX3000 PCle WiFi Card", 85.3, "New","Internet card");
        Item item6 = new Item(6,"Arctic F12-120 fan", 22.9, "Used", "Fan");

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

    public ArrayList<Item> findWithFilters(SearchItemParameters parameters)
    {
        if (parameters.isEmpty())
            return new ArrayList<Item>(itemMap.values());

        ArrayList<Item> result = new ArrayList<>();
        for (Item a : itemMap.values())
        {
            if (Objects.equals(parameters.getType(), a.getType())
                    || Objects.equals(parameters.getCondition(), a.getCondition())
            )
            //add more potential filters here
            {
                result.add(a);
            }
        }
        return result;
    }
}
