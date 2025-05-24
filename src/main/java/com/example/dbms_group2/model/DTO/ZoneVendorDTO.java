package com.example.dbms_group2.model.DTO;

public class ZoneVendorDTO {
    private String displayName; // 顯示名稱，例如「攤商A（文創）」
    private String boothCode;   // 攤商編碼，就是地圖上的編號就可以！

    public ZoneVendorDTO(String displayName, String boothCode) {
        this.displayName = displayName;
        this.boothCode = boothCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBoothCode() {
        return boothCode;
    }

    public void setBoothCode(String boothCode) {
        this.boothCode = boothCode;
    }
}
