package com.example.dbms_group2.model.DTO;

public class MarketInfoDTO {
    private Long id;                 // 市集 id（供招商連結用）
    private String name;            // 市集名稱
    private String organizer;       // 主辦單位
    private String dateRange;       // 日期範圍 (2025/6/1 - 2025/6/3)
    private String boothName;       // 攤位名稱
    private Long applyId;           // 每筆申請對應的唯一 ID
    private String statusText;      // 狀態文字（審核中、已通過、未通過、招商中）
    private String statusCode;      // 狀態文字同上，因為前端的關係

    public MarketInfoDTO(Long id, String name, String organizer, String dateRange, String boothName, Long applyId, String statusText, String statusCode) {
        this.id = id;
        this.name = name;
        this.organizer = organizer;
        this.dateRange = dateRange;
        this.boothName = boothName;
        this.applyId = applyId;
        this.statusText = statusText;
        this.statusCode = statusCode;
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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getBoothName() {
        return boothName;
    }

    public void setBoothName(String boothName) {
        this.boothName = boothName;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
