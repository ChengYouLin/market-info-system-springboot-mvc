package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQueryRepository extends JpaRepository<Announcement, Long> {

    //List<Announcement> findMarketAnnouncement(int organizerId);
}
