package com.example.dbms_group2.model.DTO;

public class LeftoverInsideDTO {
    private int id;               // 剩食 ID（主鍵）
    private String productName;    // 商品名稱
    private int leftCount;     // 剩餘份數（顯示用）

    public LeftoverInsideDTO(int id, String productName, int leftCount) {
        this.id = id;
        this.productName = productName;
        this.leftCount = leftCount;
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

    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }
}
