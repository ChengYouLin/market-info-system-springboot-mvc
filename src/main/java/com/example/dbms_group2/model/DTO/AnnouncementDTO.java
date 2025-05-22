package com.example.dbms_group2.model.DTO;

import java.time.LocalDateTime;

public class AnnouncementDTO {
    private String marketName;
    private LocalDateTime time;
    private String title;
    private String content;

    public AnnouncementDTO(String marketName, LocalDateTime time, String title, String content) {
        this.marketName = marketName;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
