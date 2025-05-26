package com.example.dbms_group2.model.DTO;

import java.math.BigDecimal;
import java.util.List;

public class LeftoverDTO {
    private Long id;               // 剩食 ID（主鍵）
    private String productName;    // 商品名稱
    private BigDecimal leftCount;     // 剩餘份數（顯示用）
    private List<RecordDTO> records; // 取餐紀錄清單


    public LeftoverDTO(Long id, String productName, BigDecimal leftCount, List<RecordDTO> records) {
        this.id = id;
        this.productName = productName;
        this.leftCount = leftCount;
        this.records = records;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(BigDecimal leftCount) {
        this.leftCount = leftCount;
    }

    public List<RecordDTO> getRecords() {
        return records;
    }

    public void setRecords(List<RecordDTO> records) {
        this.records = records;
    }
}
