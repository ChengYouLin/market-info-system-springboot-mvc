package com.example.dbms_group2.model.DTO;

public class LotteryInsideDTO {

    private long id;
    private String title;              // 抽獎主題
    private String rule;               // 抽獎規則

    public LotteryInsideDTO(long id, String title, String rule) {
        this.id = id;
        this.title = title;
        this.rule = rule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}

