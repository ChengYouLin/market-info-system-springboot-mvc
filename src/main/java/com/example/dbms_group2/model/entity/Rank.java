package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private int rankId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rank_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rank_apply"))
    private Apply apply;

    @Column(name = "rank_score")
    private int rankScore;

    public Rank() {
    }

    public Rank(int rankId, User user, Apply apply, int rankScore) {
        this.rankId = rankId;
        this.user = user;
        this.apply = apply;
        this.rankScore = rankScore;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
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

    public int getRankScore() {
        return rankScore;
    }

    public void setRankScore(int rankScore) {
        this.rankScore = rankScore;
    }
}
