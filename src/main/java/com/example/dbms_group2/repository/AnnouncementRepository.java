package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    @Modifying
    @Query(value = """
            INSERT INTO announcement (organizer_id, title, content, target)
            SELECT mar.organizer_id,
                    :title,
                    :content,
                    :target
            FROM market mar
            JOIN organizer org ON mar.organizer_id = org.organizer_id
            WHERE org.gmail = :email
            LIMIT 1
            """, nativeQuery = true)
    void create(String title, String content, String target, String email);

    @Modifying
    @Query(value = """
            UPDATE announcement AS a
            SET a.title = :title,
            a.content = :content,
            a.target = :target
            WHERE a.announcement_id = :id
            """, nativeQuery = true)
    void update(int id, String title, String content, String target, String email);

    @Modifying
    @Query(value = """
            DELETE FROM announcement WHERE announcement_id = :id""", nativeQuery = true)
    void delete(int id);
}
