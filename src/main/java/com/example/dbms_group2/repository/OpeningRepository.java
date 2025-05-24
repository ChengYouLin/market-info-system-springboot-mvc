package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.TimeSlotDTO;
import com.example.dbms_group2.model.entity.Opening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpeningRepository extends JpaRepository<Opening, Long> {

    @Query("""
            SELECT new com.example.dbms_group2.model.DTO.TimeSlotDTO(o.date, o.startTime, o.endTime) FROM Opening o
                WHERE o.market.marketId = :marketId
            """)
    List<TimeSlotDTO> findTimeSlot(int marketId);
}
