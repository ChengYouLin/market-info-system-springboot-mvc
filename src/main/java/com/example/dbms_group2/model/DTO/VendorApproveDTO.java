package com.example.dbms_group2.model.DTO;

public class VendorApproveDTO {
    private int id;           // 攤商 ID
    private String name;       // 攤商名稱
    private String email;      // 攤商信箱
    private String boothCode;  // 攤位編號（如 "A1"）
    private boolean checkedIn; // 是否已報到

    public VendorApproveDTO(int id, String name, String email, String boothCode, boolean checkedIn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.boothCode = boothCode;
        this.checkedIn = checkedIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getBoothCode() {
        return boothCode;
    }

    public void setBoothCode(String boothCode) {
        this.boothCode = boothCode;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
