package com.example.dbms_group2.model.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "market")
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private int marketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_market_organizer"))
    private Organizer organizer;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "recruit_start_time")
    private LocalDateTime recruitStartTime;

    @Column(name = "recruit_end_time")
    private LocalDateTime recruitEndTime;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "banner")
    private String banner;

    @Column(name = "email")
    private String email;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "line")
    private String line;

    @Column(name = "website")
    private String website;

    @Column(name = "lottery_rule")
    private String lotteryRule;

    @Column(name = "lottery_title")
    private String lotteryTitle;

    @Column(name = "location")
    private String location;

    @Column(name = "imageURL")
    private String imageUrl;


    public Market() {
    }

    public Market(int marketId, Organizer organizer, String name, String status, LocalDateTime recruitStartTime, LocalDateTime recruitEndTime, String description, String banner, String email, String facebook, String instagram, String line, String website, String lotteryRule, String lotteryTitle, String location, String imageUrl) {
        this.marketId = marketId;
        this.organizer = organizer;
        this.name = name;
        this.status = status;
        this.recruitStartTime = recruitStartTime;
        this.recruitEndTime = recruitEndTime;
        this.description = description;
        this.banner = banner;
        this.email = email;
        this.facebook = facebook;
        this.instagram = instagram;
        this.line = line;
        this.website = website;
        this.lotteryRule = lotteryRule;
        this.lotteryTitle = lotteryTitle;
        this.location = location;
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRecruitStartTime() {
        return recruitStartTime;
    }

    public void setRecruitStartTime(LocalDateTime recruitStartTime) {
        this.recruitStartTime = recruitStartTime;
    }

    public LocalDateTime getRecruitEndTime() {
        return recruitEndTime;
    }

    public void setRecruitEndTime(LocalDateTime recruitEndTime) {
        this.recruitEndTime = recruitEndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLotteryRule() {
        return lotteryRule;
    }

    public void setLotteryRule(String lotteryRule) {
        this.lotteryRule = lotteryRule;
    }

    public String getLotteryTitle() {
        return lotteryTitle;
    }

    public void setLotteryTitle(String lotteryTitle) {
        this.lotteryTitle = lotteryTitle;
    }


}
