package com.example.dbms_group2.model.DTO;
public class MapDownDTO {
    private String mapId; // 分區代碼
    private String vendorName; // 攤商名稱
    private int mapNum; // 地圖上的編號

    public MapDownDTO() {

    }

    public MapDownDTO(String mapId, String vendorName, int mapNum) {
        this.mapId = mapId;
        this.vendorName = vendorName;
        this.mapNum = mapNum;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getMapNum() {
        return mapNum;
    }

    public void setMapNum(int mapNum) {
        this.mapNum = mapNum;
    }
}