package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcement")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private int announcementId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_announcement_organizer"))
    private Organizer organizer;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    //@Enumerated(EnumType.STRING)
    @Column(name = "target")
    private String target;

    public Announcement() {
    }

    public Announcement(int announcementId, Organizer organizer, String title, String content, LocalDateTime updateTime, String target) {
        this.announcementId = announcementId;
        this.organizer = organizer;
        this.title = title;
        this.content = content;
        this.updateTime = updateTime;
        this.target = target;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
