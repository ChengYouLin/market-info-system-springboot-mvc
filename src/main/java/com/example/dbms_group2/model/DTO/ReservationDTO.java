package com.example.dbms_group2.model.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationDTO {
    private int id;
    private String productName;
    private LocalDateTime pickupTime;
    private String vendor;
    private int quantity;

    public ReservationDTO(int id, String productName, LocalDateTime pickupTime, String vendor, int quantity) {
        this.id = id;
        this.productName = productName;
        this.pickupTime = pickupTime;
        this.vendor = vendor;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getQuantity() {
        return quantity;
    }
}
