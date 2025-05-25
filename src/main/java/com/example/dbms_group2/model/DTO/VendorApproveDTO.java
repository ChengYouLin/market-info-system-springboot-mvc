package com.example.dbms_group2.model.DTO;

public class VendorApproveDTO {
    private Long id;           // 攤商 ID
    private String name;       // 攤商名稱
    private String email;      // 攤商信箱
    private Object boothCode;  // 攤位編號（如 "A1"）
    private Long checkedIn; // 是否已報到
    private Object cateName;

    public VendorApproveDTO(Long id, String name, String email, Object boothCode, Long checkedIn, Object cateName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.boothCode = boothCode;
        this.checkedIn = checkedIn;
        this.cateName = cateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getBoothCode() {
        return boothCode;
    }

    public void setBoothCode(Object boothCode) {
        this.boothCode = boothCode;
    }

    public Long getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Long checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Object getCateName() {
        return cateName;
    }

    public void setCateName(Object cateName) {
        this.cateName = cateName;
    }
}
