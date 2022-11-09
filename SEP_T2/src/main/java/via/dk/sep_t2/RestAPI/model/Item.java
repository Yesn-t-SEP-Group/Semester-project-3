package via.dk.sep_t2.RestAPI.model;

public class Item {
    private int id;
    private String name;
    private double price;
    private String condition;

    private String type;

    public Item(int id, String name, double price, String conditio, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.condition = condition;
        this.type = type;
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

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", condition='" + condition + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
