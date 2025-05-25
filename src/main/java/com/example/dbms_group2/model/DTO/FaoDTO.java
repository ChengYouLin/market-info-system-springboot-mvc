package com.example.dbms_group2.model.DTO;

public class FaoDTO {
    private String vendorName;
    private String marketName;
    private Long vendorId;

    public FaoDTO(String vendorName, String marketName, Long vendorId) {
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

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}
