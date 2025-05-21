package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private int vendorId;

    @Column(name = "name")
    private String name;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "password")
    private String password;

    public Vendor() {
    }

    public Vendor(String name, String telephone, String gmail, String password) {
        this.name = name;
        this.telephone = telephone;
        this.gmail = gmail;
        this.password = password;
    }

    public int getVendorId() {
        return vendorId;
    }
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
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

}
