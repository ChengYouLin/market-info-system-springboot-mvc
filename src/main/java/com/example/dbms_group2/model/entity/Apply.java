package com.example.dbms_group2.model.entity;
import java.time.*;

import jakarta.persistence.*;

@Entity
@Table(name = "apply")
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private int applyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_apply_vendor"))
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", nullable = false, foreignKey = @ForeignKey(name = "fk_apply_market"))
    private Market market;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "line")
    private String line;

    @Column(name = "website")
    private String website;

    @Lob
    @Column(name = "logo")
    private String logo;

    @Column(name = "status")
    private String status;

    @Lob
    @Column(name = "qrcode")
    private String qrcode;

    @Column(name = "stamp")
    private String stamp;

    public Apply() {}

    public Apply(int applyId, Vendor vendor, Market market, LocalDate date, String name, String description, String email,
                 String facebook, String instagram, String line, String website, String logo,
                 String status, String qrcode, String stamp) {
        this.applyId = applyId;
        this.vendor = vendor;
        this.market = market;
        this.date = date;
        this.name = name;
        this.description = description;
        this.email = email;
        this.facebook = facebook;
        this.instagram = instagram;
        this.line = line;
        this.website = website;
        this.logo = logo;
        this.status = status;
        this.qrcode = qrcode;
        this.stamp = stamp;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }
}
