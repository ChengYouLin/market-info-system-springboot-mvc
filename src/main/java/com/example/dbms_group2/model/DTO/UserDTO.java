package com.example.dbms_group2.model.DTO;

public class UserDTO {
    private String name;
    private String gmail;
    private String phone;

    public UserDTO(String name, String gmail, String phone) {
        this.name = name;
        this.gmail = gmail;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
