package com.example.dbms_group2.model.DTO;

public class LeftoverFoodDTO {
    private Long leftoverId;
    private String productName;
    private String vendor;       // 攤商名稱
    private String category;     // 食物類別
    private int remaining;       // 剩餘份數

    public LeftoverFoodDTO(Long leftoverId, String productName, String vendor, String category, int remaining) {
        this.leftoverId = leftoverId;
        this.productName = productName;
        this.vendor = vendor;
        this.category = category;
        this.remaining = remaining;
    }

    public Long getLeftoverId() {
        return leftoverId;
    }

    public void setLeftoverId(Long leftoverId) {
        this.leftoverId = leftoverId;
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

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
