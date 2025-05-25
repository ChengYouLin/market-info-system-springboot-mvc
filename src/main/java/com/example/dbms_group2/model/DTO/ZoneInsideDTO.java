package com.example.dbms_group2.model.DTO;

import java.util.List;

public class ZoneInsideDTO {
    private String code;                     // 分區代號（0~4）
    private String name;                     // 分區名稱（如 文創、食品）
    private List<ZoneVendorDTO> vendors;     // 分區底下的攤商（用於 zone-columns 顯示）

    public ZoneInsideDTO(String code, String name) {
        this.code = code;
        this.name = name;
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

}
