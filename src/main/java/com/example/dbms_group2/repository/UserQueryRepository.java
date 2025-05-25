package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.AnnouncementDTO;
import com.example.dbms_group2.model.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserQueryRepository extends JpaRepository<Announcement, Long> {

    @Query("""
                SELECT new com.example.dbms_group2.model.DTO.AnnouncementDTO(
                    m.name, 
                    a.updateTime, 
                    a.title, 
                    a.content 
                )
                FROM Announcement a
                JOIN Market m ON m.organizer = a.organizer
                WHERE a.organizer.organizerId = :organizerId 
                  AND a.target = '一般使用者'
                ORDER BY a.updateTime DESC
            """)
    List<AnnouncementDTO> findMarketAnnouncement(@Param("organizerId") int organizerId);

    @Query(value = """
            SELECT 
                m.name AS marketName,
                a.update_time AS time,
                a.title AS title,
                a.content AS content
            FROM vendor v 
            JOIN apply ap ON ap.vendor_id = v.vendor_id
            JOIN market m ON ap.market_id = m.market_id
            JOIN organizer o ON o.organizer_id = m.organizer_id
            JOIN announcement a ON a.organizer_id = o.organizer_id
            WHERE v.gmail = :vendorEmail
              AND ap.status = '已通過'
              AND a.target IN ('攤商')
            ORDER BY a.update_time DESC
            """, nativeQuery = true)
    List<AnnouncementDTO> findMarketForVendorAnnouncement(String vendorEmail);
}
