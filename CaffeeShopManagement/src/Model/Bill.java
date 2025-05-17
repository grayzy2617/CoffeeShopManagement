package Model;

import java.security.Timestamp;
import java.time.LocalDateTime;

public class Bill {
    private Integer id;
    private String phoneNumberCus;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private Integer createdByEmployID;

    public Bill(Integer id, String phoneNumberCus, Double totalPrice, LocalDateTime createdAt, Integer createdByEmployID) {
        this.id = id;
        this.phoneNumberCus = phoneNumberCus;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.createdByEmployID = createdByEmployID;
    }

    public Integer getId() { return id; }
    public String getPhoneNumberCus() { return phoneNumberCus; }
    public Double getTotalPrice() { return totalPrice; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Integer getCreatedByEmployID() { return createdByEmployID; }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhoneNumberCus(String phoneNumberCus) {
        this.phoneNumberCus = phoneNumberCus;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedByEmployID(int createdByEmployID) {
        this.createdByEmployID = createdByEmployID;
    }
}
