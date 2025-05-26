package com.example.dbms_group2.model.DTO;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RecordDTO {

    private Long id;             // 紀錄 ID
    private Long pickupCode;   // 取餐編號
    private String buyer;        // 訂購人名稱
    private Integer quantity;    // 訂購份數
    private Timestamp pickupTime;   // 最後取餐時間（字串格式）

    public RecordDTO(Long id, Long pickupCode, String buyer, Integer quantity, Timestamp pickupTime) {
        this.id = id;
        this.pickupCode = pickupCode;
        this.buyer = buyer;
        this.quantity = quantity;
        this.pickupTime = pickupTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(Long pickupCode) {
        this.pickupCode = pickupCode;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Timestamp getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Timestamp pickupTime) {
        this.pickupTime = pickupTime;
    }
}
