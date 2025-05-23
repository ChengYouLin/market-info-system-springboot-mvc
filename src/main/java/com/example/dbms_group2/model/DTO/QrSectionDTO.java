package com.example.dbms_group2.model.DTO;

public class QrSectionDTO {
    private String marketName;   // 市集名稱
    private String boothName;    // 攤位名稱
    private String stampCode;    // 集點代碼

    public QrSectionDTO(String marketName, String boothName, String stampCode) {
        this.marketName = marketName;
        this.boothName = boothName;
        this.stampCode = stampCode;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getBoothName() {
        return boothName;
    }

    public void setBoothName(String boothName) {
        this.boothName = boothName;
    }

    public String getStampCode() {
        return stampCode;
    }

    public void setStampCode(String stampCode) {
        this.stampCode = stampCode;
    }
}