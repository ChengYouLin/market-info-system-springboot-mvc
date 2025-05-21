package com.example.dbms_group2.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "organizer")
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizer_id")
    private int organizerId;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "password")
    private String password;

    @Column(name = "lottery")
    private boolean lottery;

    @Column(name = "product")
    private boolean product;

    @Column(name = "leftover")
    private boolean leftover;

    @Column(name = "map_version")
    private boolean mapVersion;

    public Organizer() {
    }

    public Organizer(int organizerId, String gmail, String password, boolean lottery, boolean product, boolean leftover, boolean mapVersion) {
        this.organizerId = organizerId;
        this.gmail = gmail;
        this.password = password;
        this.lottery = lottery;
        this.product = product;
        this.leftover = leftover;
        this.mapVersion = mapVersion;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
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

    public boolean isLottery() {
        return lottery;
    }

    public void setLottery(boolean lottery) {
        this.lottery = lottery;
    }

    public boolean isProduct() {
        return product;
    }

    public void setProduct(boolean product) {
        this.product = product;
    }

    public boolean isLeftover() {
        return leftover;
    }

    public void setLeftover(boolean leftover) {
        this.leftover = leftover;
    }

    public boolean isMapVersion() {
        return mapVersion;
    }

    public void setMapVersion(boolean mapVersion) {
        this.mapVersion = mapVersion;
    }
}
