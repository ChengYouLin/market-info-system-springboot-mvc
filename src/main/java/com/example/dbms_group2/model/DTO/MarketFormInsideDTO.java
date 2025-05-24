package com.example.dbms_group2.model.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MarketFormInsideDTO {
    private String marketName;
    private String location;

    private LocalDate recruitStartDate;
    private LocalTime recruitStartTime;
    private LocalDate recruitEndDate;
    private LocalTime recruitEndTime;

    private String email;
    private String facebook;
    private String instagram;
    private String line;
    private String website;

    public MarketFormInsideDTO(String marketName, String location, LocalDate recruitStartDate, LocalTime recruitStartTime, LocalDate recruitEndDate, LocalTime recruitEndTime, String email, String facebook, String instagram, String line, String website) {
        this.marketName = marketName;
        this.location = location;
        this.recruitStartDate = recruitStartDate;
        this.recruitStartTime = recruitStartTime;
        this.recruitEndDate = recruitEndDate;
        this.recruitEndTime = recruitEndTime;
        this.email = email;
        this.facebook = facebook;
        this.instagram = instagram;
        this.line = line;
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public LocalDate getRecruitStartDate() {
        return recruitStartDate;
    }

    public void setRecruitStartDate(LocalDate recruitStartDate) {
        this.recruitStartDate = recruitStartDate;
    }

    public LocalTime getRecruitStartTime() {
        return recruitStartTime;
    }

    public void setRecruitStartTime(LocalTime recruitStartTime) {
        this.recruitStartTime = recruitStartTime;
    }

    public LocalDate getRecruitEndDate() {
        return recruitEndDate;
    }

    public void setRecruitEndDate(LocalDate recruitEndDate) {
        this.recruitEndDate = recruitEndDate;
    }

    public LocalTime getRecruitEndTime() {
        return recruitEndTime;
    }

    public void setRecruitEndTime(LocalTime recruitEndTime) {
        this.recruitEndTime = recruitEndTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
