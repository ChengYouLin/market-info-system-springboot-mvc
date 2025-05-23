package com.example.dbms_group2.model.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlotDTO {
    private LocalDate date;       // 例如：2024/06/01
    private LocalTime startTime;  // 例如：11:00
    private LocalTime endTime;

    public TimeSlotDTO(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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
