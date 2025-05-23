package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.AnnouncementDTO;
import com.example.dbms_group2.model.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQueryRepository extends JpaRepository<Announcement, Long> {

    List<AnnouncementDTO> findMarketAnnouncement(int organizerId);
    List<AnnouncementDTO> findMarketForVendorAnnouncement(String vendorEmail);
}
