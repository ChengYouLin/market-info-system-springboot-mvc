package com.example.dbms_group2.model.DTO;

import java.util.List;

public class LotteryInsideDTO {

    private int id;
    private String title;              // 抽獎主題
    private String rule;               // 抽獎規則

    public LotteryInsideDTO(int id, String title, String rule) {
        this.id = id;
        this.title = title;
        this.rule = rule;
    }

    public LotteryInsideDTO() {

    }

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

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}

