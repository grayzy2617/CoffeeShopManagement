package Model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private String unit;

    public Product(int id, String name, double price, int categoryId, String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.unit = unit;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getCategoryId() { return categoryId; }
    public String getUnit() { return unit; }
}
