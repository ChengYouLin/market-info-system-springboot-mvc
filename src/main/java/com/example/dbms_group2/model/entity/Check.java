package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "`check`")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_id")
    private int checkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", foreignKey = @ForeignKey(name = "fk_check_organizer"))
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id", foreignKey = @ForeignKey(name = "fk_check_apply"))
    private Apply apply;

    @Column(name = "status")
    private boolean status = false;

    public Check() {
    }

    public Check(int checkId, Organizer organizer, Apply apply, boolean status) {
        this.checkId = checkId;
        this.organizer = organizer;
        this.apply = apply;
        this.status = status;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
