package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private int zoneId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", foreignKey = @ForeignKey(name = "fk_zone_organizer"))
    private Organizer organizer;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public Zone() {
    }

    public Zone(int zoneId, Organizer organizer, String code, String name) {
        this.zoneId = zoneId;
        this.organizer = organizer;
        this.code = code;
        this.name = name;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
