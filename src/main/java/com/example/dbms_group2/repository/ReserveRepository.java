package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.LeftoverFoodDTO;
import com.example.dbms_group2.model.DTO.ReservationDTO;
import com.example.dbms_group2.model.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    List<ReservationDTO> getReserInfo(String email, int marketId);

    List<LeftoverFoodDTO> getLeftoverInfo(String email);

    void deleteThisRecord(int recordId);
}
