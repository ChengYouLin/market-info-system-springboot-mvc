package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "reserve")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    private int reserveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leftover_id", foreignKey = @ForeignKey(name = "fk_reserve_leftover"))
    private Leftover leftover;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_reserve_user"))
    private User user;

    @Column(name = "amount")
    private int amount;

    @Column(name = "last_time")
    private LocalDateTime lastTime;

    public Reserve() {
    }

    public Reserve(int reserveId, Leftover leftover, User user, int amount, LocalDateTime lastTime) {
        this.reserveId = reserveId;
        this.leftover = leftover;
        this.user = user;
        this.amount = amount;
        this.lastTime = lastTime;
    }

    public int getReserveId() {
        return reserveId;
    }

    public void setReserveId(int reserveId) {
        this.reserveId = reserveId;
    }

    public Leftover getLeftover() {
        return leftover;
    }

    public void setLeftover(Leftover leftover) {
        this.leftover = leftover;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }
}
