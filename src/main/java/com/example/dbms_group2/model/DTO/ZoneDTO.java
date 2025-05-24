package com.example.dbms_group2.model.DTO;

import java.util.List;

public class ZoneDTO {
    private String code;                     // 分區代號（0~4）
    private String name;                     // 分區名稱（如 文創、食品）
    private List<ZoneVendorDTO> vendors;     // 分區底下的攤商（用於 zone-columns 顯示）

    public ZoneDTO(String code, String name, List<ZoneVendorDTO> vendors) {
        this.code = code;
        this.name = name;
        this.vendors = vendors;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ZoneVendorDTO> getVendors() {
        return vendors;
    }

    public void setVendors(List<ZoneVendorDTO> vendors) {
        this.vendors = vendors;
    }
}
