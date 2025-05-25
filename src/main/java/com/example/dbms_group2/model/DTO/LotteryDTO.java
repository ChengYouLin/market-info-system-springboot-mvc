package com.example.dbms_group2.model.DTO;

import java.util.List;

public class LotteryDTO {

    private Long id;
    private String title;              // 抽獎主題
    private String rule;               // 抽獎規則
    private List<RewardDTO> rewards;

    public LotteryDTO(Long id, String title, String rule, List<RewardDTO> rewards) {
        this.id = id;
        this.title = title;
        this.rule = rule;
        this.rewards = rewards;
    }

    public LotteryDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<RewardDTO> getRewards() {
        return rewards;
    }

    public void setRewards(List<RewardDTO> rewards) {
        this.rewards = rewards;
    }
}
