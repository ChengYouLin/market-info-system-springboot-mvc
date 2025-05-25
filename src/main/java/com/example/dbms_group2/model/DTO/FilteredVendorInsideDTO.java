package com.example.dbms_group2.model.DTO;

public class FilteredVendorInsideDTO {
    private int code; // 地圖編號 1 ~ 20（重要！）
    private int vendorId;
    private String name;

    public FilteredVendorInsideDTO(int code, int vendorId, String name) {
        this.code = code;
        this.vendorId = vendorId;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
