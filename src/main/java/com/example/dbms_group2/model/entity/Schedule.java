package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private int scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", foreignKey = @ForeignKey(name = "fk_schedule_organizer"))
    private Organizer organizer;

    @Lob
    @Column(name = "schedule_picture")
    private String schedulePicture;

    public Schedule() {
    }

    public Schedule(int scheduleId, Organizer organizer, String schedulePicture) {
        this.scheduleId = scheduleId;
        this.organizer = organizer;
        this.schedulePicture = schedulePicture;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public String getSchedulePicture() {
        return schedulePicture;
    }

    public void setSchedulePicture(String schedulePicture) {
        this.schedulePicture = schedulePicture;
    }
}
