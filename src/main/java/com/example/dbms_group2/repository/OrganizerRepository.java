package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizerRepository extends JpaRepository<Organizer,Long> {

    @Query("SELECT o FROM Organizer o WHERE o.gmail = :email")
    List<Organizer> findByGmail(@Param("email") String Email);

}
