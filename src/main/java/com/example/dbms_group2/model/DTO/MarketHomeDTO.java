package com.example.dbms_group2.model.DTO;

public class MarketHomeDTO {
    private String title;        // 市集名稱
    private String description;  // 市集介紹
    private String location;     // 地點
    private String imageUrl;     // 市集（banner)封面圖片
    private String schedule1;    // 節目表圖片1（可為 null）
    private String schedule2;    // 節目表圖片2（可為 null）
    private String schedule3;
    private String email;
    private String instagram;
    private String facebook;
    private String website;
    private String line;

    public MarketHomeDTO(String title, String description, String location, String imageUrl, String schedule1, String schedule2, String schedule3, String email, String instagram, String facebook, String website, String line) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
        this.schedule1 = schedule1;
        this.schedule2 = schedule2;
        this.schedule3 = schedule3;
        this.email = email;
        this.instagram = instagram;
        this.facebook = facebook;
        this.website = website;
        this.line = line;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSchedule1() {
        return schedule1;
    }

    public void setSchedule1(String schedule1) {
        this.schedule1 = schedule1;
    }

    public String getSchedule2() {
        return schedule2;
    }

    public void setSchedule2(String schedule2) {
        this.schedule2 = schedule2;
    }

    public String getSchedule3() {
        return schedule3;
    }

    public void setSchedule3(String schedule3) {
        this.schedule3 = schedule3;
    }
}
