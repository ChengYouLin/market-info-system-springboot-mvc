package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "leftover")
public class Leftover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leftover_id")
    private int leftoverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", foreignKey = @ForeignKey(name = "fk_leftover_vendor"))
    private Vendor vendor;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private int amount = 0;

    //@Enumerated(EnumType.STRING)
    @Column(name = "status")
    private String status;

    //@Enumerated(EnumType.STRING)
    @Column(name = "type")
    private String type;

    public Leftover() {
    }

    public Leftover(int leftoverId, Vendor vendor, String name, int amount, String status, String type) {
        this.leftoverId = leftoverId;
        this.vendor = vendor;
        this.name = name;
        this.amount = amount;
        this.status = status;
        this.type = type;
    }

    public int getLeftoverId() {
        return leftoverId;
    }

    public void setLeftoverId(int leftoverId) {
        this.leftoverId = leftoverId;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
