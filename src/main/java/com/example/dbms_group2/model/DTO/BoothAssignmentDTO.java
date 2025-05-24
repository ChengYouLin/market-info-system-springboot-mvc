package com.example.dbms_group2.model.DTO;

public class BoothAssignmentDTO {
    private int boothCode; // 要更新的攤位編號（如 "5"）
    private int vendorId;    // 指派的攤商 ID
    private String category;  // 選擇的攤商類別

    public BoothAssignmentDTO(int boothCode, int vendorId, String category) {
        this.boothCode = boothCode;
        this.vendorId = vendorId;
        this.category = category;
    }

    public int getBoothCode() {
        return boothCode;
    }

    public void setBoothCode(int boothCode) {
        this.boothCode = boothCode;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
