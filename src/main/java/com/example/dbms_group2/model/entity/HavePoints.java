package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "have_points")
public class HavePoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "have_points_id")
    private int havePointsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_have_points_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", nullable = false, foreignKey = @ForeignKey(name = "fk_have_points_market"))
    private Market market;

    @Column(name = "points", nullable = false)
    private int points;

    public HavePoints() {
    }

    public HavePoints(int havePointsId, User user, Market market, int points){
        this.havePointsId = havePointsId;
    }

    public int getHavePointsId() {
        return havePointsId;
    }

    public void setHavePointsId(int havePointsId) {

        this.havePointsId = havePointsId;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Market getMarket() {
        return market;
    }
    public void setMarket(Market market) {
        this.market = market;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


}