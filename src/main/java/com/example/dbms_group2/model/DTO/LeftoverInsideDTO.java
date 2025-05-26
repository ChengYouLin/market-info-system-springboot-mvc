package com.example.dbms_group2.model.DTO;

import java.math.BigDecimal;

public class LeftoverInsideDTO {
    private Long id;               // 剩食 ID（主鍵）
    private String productName;    // 商品名稱
    private BigDecimal leftCount;     // 剩餘份數（顯示用）

    public LeftoverInsideDTO(Long id, String productName, BigDecimal leftCount) {
        this.id = id;
        this.productName = productName;
        this.leftCount = leftCount;
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
}
