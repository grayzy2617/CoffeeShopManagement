package Model;

import java.security.Timestamp;

public class Bill {
    private int id;
    private String phoneNumberCus;
    private double totalPrice;
    private Timestamp createdAt;
    private int createdByEmployID;

    public Bill(int id, String phoneNumberCus, double totalPrice, Timestamp createdAt, int createdByEmployID) {
        this.id = id;
        this.phoneNumberCus = phoneNumberCus;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.createdByEmployID = createdByEmployID;
    }

    public int getId() { return id; }
    public String getPhoneNumberCus() { return phoneNumberCus; }
    public double getTotalPrice() { return totalPrice; }
    public Timestamp getCreatedAt() { return createdAt; }
    public int getCreatedByEmployID() { return createdByEmployID; }
}
