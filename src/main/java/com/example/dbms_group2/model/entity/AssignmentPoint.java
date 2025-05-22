package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "assignment_point")
public class lAssignmentPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_point_id")
    private int assignmentPointId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", foreignKey = @ForeignKey(name = "fk_assignment_point_zone"))
    private Zone zone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id", foreignKey = @ForeignKey(name = "fk_assignment_point_apply"))
    private Apply apply;

    @Column(name = "num")
    private int num;

    public AssignmentPoint() {
    }

    public AssignmentPoint(int assignmentPointId, Zone zone, Apply apply, int num) {
        this.assignmentPointId = assignmentPointId;
        this.zone = zone;
        this.apply = apply;
        this.num = num;
    }

    public int getAssignmentPointId() {
        return assignmentPointId;
    }

    public void setAssignmentPointId(int assignmentPointId) {
        this.assignmentPointId = assignmentPointId;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
