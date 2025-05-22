package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizerRepository extends JpaRepository<Organizer,Long> {

    List<Organizer> findByGmail(String Email);

}
