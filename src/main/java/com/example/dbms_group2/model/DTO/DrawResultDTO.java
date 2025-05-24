package com.example.dbms_group2.model.DTO;

public class DrawResultDTO {
    private String userName;     // 中獎者
    private String rewardName;   // 中到的獎品

    public DrawResultDTO(String userName, String rewardName) {

        this.userName = userName;
        this.rewardName = rewardName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }
}
