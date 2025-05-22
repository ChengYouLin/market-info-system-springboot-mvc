package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "prize")
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prize_id")
    private int prizeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", nullable = false, foreignKey = @ForeignKey(name = "fk_prize_market"))
    private Market market;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private int amount;

    public Prize() {
    }

    public Prize(int prizeId, Market market, String name, int amount) {
        this.prizeId = prizeId;
        this.market = market;
        this.name = name;
        this.amount = amount;
    }

    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
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
}
