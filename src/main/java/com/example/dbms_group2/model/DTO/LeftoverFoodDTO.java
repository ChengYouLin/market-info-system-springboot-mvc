package com.example.dbms_group2.model.DTO;

public class LeftoverFoodDTO {
    private String productName;
    private String vendor;       // 攤商名稱
    private String category;     // 食物類別
    private Object remaining;       // 剩餘份數
    private Object leftoverId;
    private Object maxQty;

    public LeftoverFoodDTO(String productName, String vendor, String category, Object remaining, Object leftoverId, Object maxQty) {
        this.productName = productName;
        this.vendor = vendor;
        this.category = category;
        this.remaining = remaining;
        this.leftoverId = leftoverId;
        this.maxQty = maxQty;
    }

    public Object getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(Object maxQty) {
        this.maxQty = maxQty;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getRemaining() {
        return remaining;
    }

    public void setRemaining(Object remaining) {
        this.remaining = remaining;
    }

    public Object getLeftoverId() {
        return leftoverId;
    }

    public void setLeftoverId(Object leftoverId) {
        this.leftoverId = leftoverId;
    }
}
