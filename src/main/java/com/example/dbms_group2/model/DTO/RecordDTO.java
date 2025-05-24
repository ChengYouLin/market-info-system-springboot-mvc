package com.example.dbms_group2.model.DTO;

import java.time.LocalTime;

public class RecordDTO {

    private int id;             // 紀錄 ID
    private String pickupCode;   // 取餐編號
    private String buyer;        // 訂購人名稱
    private int quantity;    // 訂購份數
    private LocalTime pickupTime;   // 最後取餐時間（字串格式）

    public RecordDTO(int id, String pickupCode, String buyer, int quantity, LocalTime pickupTime) {
        this.id = id;
        this.pickupCode = pickupCode;
        this.buyer = buyer;
        this.quantity = quantity;
        this.pickupTime = pickupTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalTime pickupTime) {
        this.pickupTime = pickupTime;
    }
}
