package Model;

public class OrderItem {
    private int id;
    private int invoiceId;
    private int productId;
    private int quantity;
    private double price;

    public OrderItem(int id, int invoiceId, int productId, int quantity, double price) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() { return id; }
    public int getInvoiceId() { return invoiceId; }
    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }
}
