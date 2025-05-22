package com.example.dbms_group2.model.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "opening")
public class Opening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opening_id")
    private int openingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", nullable = false, foreignKey = @ForeignKey(name = "fk_opening_market"))
    private Market market;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    public Opening() {
    }

    public Opening(int openingId, Market market, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.openingId = openingId;
        this.market = market;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getOpeningId() {
        return openingId;
    }

    public void setOpeningId(int openingId) {
        this.openingId = openingId;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
