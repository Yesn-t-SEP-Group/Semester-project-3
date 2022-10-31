package via.dk.sep_t2.RestAPI.model;

public class Item {
    private int id;
    private String name;
    private double price;
    private String condition;

    public Item(int id, String name, double price, String condition) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Name:" + name + "/nPrice:" + price;
    }
}
