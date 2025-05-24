package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.DTO;
import com.example.dbms_group2.model.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizerRepository extends JpaRepository<Organizer,Long> {

    @Query("SELECT o FROM Organizer o WHERE o.gmail = :email")
    List<Organizer> findByGmail(@Param("email") String Email);

//    @Query("SELECT new com.example.dbms_group2.model.DTO.OrganizerStatusDTO(m.organizer.lottery, m.organizer.leftover, m.organizer.product) " +
//            "FROM Market m " +
//            "WHERE m.marketId = :marketId")
    List<DTO> findStatus(int marketId);


}
