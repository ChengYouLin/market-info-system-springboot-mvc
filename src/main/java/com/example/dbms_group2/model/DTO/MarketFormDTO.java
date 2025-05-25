package com.example.dbms_group2.model.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MarketFormDTO {
    private String marketName;
    private String location;
    private List<EventPeriodDTO> eventPeriods;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recruitStartDate;

    private Time recruitStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recruitEndDate;
    private Time recruitEndTime;

    private String email;
    private String facebook;
    private String instagram;
    private String line;
    private String website;

    public MarketFormDTO() {
        this.eventPeriods = new ArrayList<>();
        // 初始化最多 3 個空的 EventPeriodDTO
        for (int i = 0; i < 3; i++) {
            eventPeriods.add(new EventPeriodDTO());
        }
    }

    public MarketFormDTO(String marketName, String location, List<EventPeriodDTO> eventPeriods, Date recruitStartDate, Time recruitStartTime, Date recruitEndDate, Time recruitEndTime, String email, String facebook, String instagram, String line, String website) {
        this.marketName = marketName;
        this.location = location;
        this.eventPeriods = eventPeriods;
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

    public List<EventPeriodDTO> getEventPeriods() {
        return eventPeriods;
    }

    public void setEventPeriods(List<EventPeriodDTO> eventPeriods) {
        this.eventPeriods = eventPeriods;
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
