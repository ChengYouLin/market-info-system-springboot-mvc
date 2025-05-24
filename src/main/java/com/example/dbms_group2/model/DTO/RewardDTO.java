package com.example.dbms_group2.model.DTO;

public class RewardDTO {
    private String name;   // 獎品名稱
    private int count;     // 數量

    public RewardDTO(String name, int count) {

        this.name = name;
        this.count = count;
    }

    public RewardDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
