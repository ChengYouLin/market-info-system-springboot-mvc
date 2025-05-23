package com.example.dbms_group2.model.DTO;

import java.util.List;

public class LeftoverDTO {
    private int id;               // 剩食 ID（主鍵）
    private String productName;    // 商品名稱
    private int leftCount;     // 剩餘份數（顯示用）
    private List<RecordDTO> records; // 取餐紀錄清單

    public LeftoverDTO(int id, String productName, int leftCount, List<RecordDTO> records) {
        this.id = id;
        this.productName = productName;
        this.leftCount = leftCount;
        this.records = records;
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

    public List<RecordDTO> getRecords() {
        return records;
    }

    public void setRecords(List<RecordDTO> records) {
        this.records = records;
    }
}
