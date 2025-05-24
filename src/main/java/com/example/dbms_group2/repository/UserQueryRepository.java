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
            m.name, a.updateTime, a.title, a.content
        )
        FROM Market m
        JOIN m.organizer o
        JOIN Announcement a ON a.organizer = o
        WHERE m.marketId = :marketId
        ORDER BY a.updateTime DESC
    """)
    List<AnnouncementDTO> findMarketAnnouncements(@Param("marketId") int marketId);
}
