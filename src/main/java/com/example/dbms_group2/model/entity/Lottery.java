package com.example.dbms_group2.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lottery")
public class Lottery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lottery_id")
    private int lotteryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_lottery_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prize_id", nullable = false, foreignKey = @ForeignKey(name = "fk_lottery_prize"))
    private Prize prize;

    public Lottery() {
    }

    public Lottery(int lotteryId, User user, Prize prize) {
        this.lotteryId = lotteryId;
        this.user = user;
        this.prize = prize;
    }

    public int getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(int lotteryId) {
        this.lotteryId = lotteryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}
