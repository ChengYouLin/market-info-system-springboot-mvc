package com.example.dbms_group2.model.DTO;

public class VendorDetailInsideDTO {
    private String title;                 // 攤商標題
    private String description;// 攤商介紹
    private Long applyId;

    public VendorDetailInsideDTO(String title, String description, Long applyId) {
        this.title = title;
        this.description = description;
        this.applyId = applyId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }
}
