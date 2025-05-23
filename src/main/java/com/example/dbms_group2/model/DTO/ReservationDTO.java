package com.example.dbms_group2.model.DTO;

import java.time.LocalTime;

public class ReservationDTO {
    private int id;
    private String productName;
    private LocalTime pickupTime;
    private String vendor;
    private int quantity;

    public ReservationDTO(int id, String productName, LocalTime pickupTime, String vendor, int quantity) {
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalTime pickupTime) {
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
