package com.example.dbms_group2.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prefer")
public class Prefer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prefer_id")
    private int preferId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_prefer_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id", nullable = false, foreignKey = @ForeignKey(name = "fk_prefer_apply"))
    private Apply apply;

    public Prefer() {
    }

    public Prefer(int preferId, User user, Apply apply) {
        this.preferId = preferId;
        this.user = user;
        this.apply = apply;
    }

    public int getPreferId() {
        return preferId;
    }

    public void setPreferId(int preferId) {
        this.preferId = preferId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }
}
