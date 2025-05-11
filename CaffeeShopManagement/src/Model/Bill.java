package Model;

import java.security.Timestamp;
import java.time.LocalDateTime;

public class Bill {
    private int id;
    private String phoneNumberCus;
    private double totalPrice;
    private LocalDateTime createdAt;
    private int createdByEmployID;

    public Bill(int id, String phoneNumberCus, double totalPrice, LocalDateTime createdAt, int createdByEmployID) {
        this.id = id;
        this.phoneNumberCus = phoneNumberCus;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.createdByEmployID = createdByEmployID;
    }

    public int getId() { return id; }
    public String getPhoneNumberCus() { return phoneNumberCus; }
    public double getTotalPrice() { return totalPrice; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public int getCreatedByEmployID() { return createdByEmployID; }
}
