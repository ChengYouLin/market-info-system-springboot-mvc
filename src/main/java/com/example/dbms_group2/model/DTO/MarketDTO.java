package com.example.dbms_group2.model.DTO;

import java.time.LocalDate;

public class MarketDTO {

    private int id;                // 市集 ID，用於連結詳細頁與申請頁
    private String title;           // 市集標題，如「夏日綠市集」
    private String description;     // 市集介紹（多行文字）
    private String location;        // 舉辦地點，如「華山文創園區」
    private LocalDate startDate;    // 開始日期
    private LocalDate endDate;      // 結束日期
    private String imageUrl;        // 圖片網址，可為 null 或空字串

    public MarketDTO(int id, String title, String description, String location, LocalDate startDate, LocalDate endDate, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
    }

    // Getter & Setter（或用 Lombok @Data）

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
