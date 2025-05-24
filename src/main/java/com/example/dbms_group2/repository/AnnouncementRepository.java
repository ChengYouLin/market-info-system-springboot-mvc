package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    void create(String title, String content, String target, String email);
    //Query
    void update(int id, String title, String content, String target, String email);

    void delete(int id);
}
