package com.example.dbms_group2.model.DTO;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class MarketFormInsideDTO {
    private String marketName;
    private String location;

    private Date recruitStartDate;
    private Time recruitStartTime;
    private Date recruitEndDate;
    private Time recruitEndTime;

    private String email;
    private String facebook;
    private String instagram;
    private String line;
    private String website;

    public MarketFormInsideDTO(String marketName, String location, Date recruitStartDate, Time recruitStartTime, Date recruitEndDate, Time recruitEndTime, String email, String facebook, String instagram, String line, String website) {
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

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getRecruitStartDate() {
        return recruitStartDate;
    }

    public void setRecruitStartDate(Date recruitStartDate) {
        this.recruitStartDate = recruitStartDate;
    }

    public Time getRecruitStartTime() {
        return recruitStartTime;
    }

    public void setRecruitStartTime(Time recruitStartTime) {
        this.recruitStartTime = recruitStartTime;
    }

    public Date getRecruitEndDate() {
        return recruitEndDate;
    }

    public void setRecruitEndDate(Date recruitEndDate) {
        this.recruitEndDate = recruitEndDate;
    }

    public Time getRecruitEndTime() {
        return recruitEndTime;
    }

    public void setRecruitEndTime(Time recruitEndTime) {
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
