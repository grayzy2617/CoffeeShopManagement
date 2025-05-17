package Model;

public class OrderItem {
    private Integer id;
    private Integer invoiceId;
    private Integer productId;
    private Integer quantity;
    private Double price;

    public OrderItem(Integer id, Integer invoiceId, Integer productId, Integer quantity, Double price) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() { return id; }
    public Integer getInvoiceId() { return invoiceId; }
    public Integer getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
    public Double getPrice() { return price; }

    public void setInvoiceId(Integer invoiceId) { this.invoiceId = invoiceId; }
    public void setproductId(Integer productId) { this.productId = productId; }
    public void setquantity(Integer quantity) { this.quantity = quantity; }
    public void setprice(Double price) { this.price = price; }
}
