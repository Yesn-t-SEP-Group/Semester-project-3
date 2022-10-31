package via.dk.sep_t2.RestAPI.model;

import java.util.ArrayList;

public class Post {
    private int id;
    private int creatorId;
    private ArrayList<Item> items = new ArrayList<>();

    private double totalPrice;
    private String description;
    private String type;

    public Post(int id, int creatorId, ArrayList<Item> items, String description, String type) {
        this.id = id;
        this.creatorId = creatorId;
        this.items = items;
        this.description = description;
        this.type = type;
        this.totalPrice = 0;
        for (Item i:items)
        {
            totalPrice+=i.getPrice();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item getItem(int index)
    {
        return items.get(index);
    }

    public void addItem(Item item)
    {
        this.items.add(item);
        totalPrice+=item.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
