package Model;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer categoryId;
    private String unit;

    public Product(Integer id, String name, Double price, Integer categoryId, String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.unit = unit;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Double getPrice() { return price; }
    public Integer getCategoryId() { return categoryId; }
    public String getUnit() { return unit; }
}
