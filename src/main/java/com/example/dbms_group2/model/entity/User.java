package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "password")
    private String password;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    public User() {
    }

    public User(int userId, String name, String telephone, String gender, String gmail, String password, byte[] picture) {
        this.userId = userId;
        this.name = name;
        this.telephone = telephone;
        this.gender = gender;
        this.gmail = gmail;
        this.password = password;
        this.picture = picture;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
