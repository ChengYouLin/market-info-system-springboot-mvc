package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.TimeSlotDTO;
import com.example.dbms_group2.model.entity.Opening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpeningRepository extends JpaRepository<Opening, Long> {

    //List<TimeSlotDTO> findTimeSlot(int marketId);
}
