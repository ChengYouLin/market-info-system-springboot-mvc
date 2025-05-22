package com.example.dbms_group2.model.DTO;

public class FaoDTO {
    private String vendorName;
    private String marketName;
    private int vendorId;

    public FaoDTO(String vendorName, String marketName, int vendorId) {
        this.vendorName = vendorName;
        this.marketName = marketName;
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
