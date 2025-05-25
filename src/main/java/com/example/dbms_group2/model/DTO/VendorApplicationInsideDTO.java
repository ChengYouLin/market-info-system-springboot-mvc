package com.example.dbms_group2.model.DTO;

import java.time.LocalDate;

public class VendorApplicationInsideDTO {

    private long id;                    // 申請 ID（用於辨識與表單提交）
    private LocalDate date;            // 申請日期
    private String email;              // 攤商信箱
    private String name;               // 攤商名稱
    private String status;             // 申請狀態（如：審核中、已通過、已拒絕）
    private String boothCode;          // 攤位編號（在 Modal 中顯示）
    private String description;        // 攤商介紹文字
    private String facebook;           // Facebook 連結
    private String instagram;          // Instagram 連結
    private String line;               // LINE 連結
    private String website;            // 官方網站連結

    public VendorApplicationInsideDTO(long id, LocalDate date, String email, String name, String status, String boothCode, String description, String facebook, String instagram, String line, String website) {
        this.id = id;
        this.date = date;
        this.email = email;
        this.name = name;
        this.status = status;
        this.boothCode = boothCode;
        this.description = description;
        this.facebook = facebook;
        this.instagram = instagram;
        this.line = line;
        this.website = website;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBoothCode() {
        return boothCode;
    }

    public void setBoothCode(String boothCode) {
        this.boothCode = boothCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
