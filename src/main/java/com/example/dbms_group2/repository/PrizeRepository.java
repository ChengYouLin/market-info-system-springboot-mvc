package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.DrawResultDTO;
import com.example.dbms_group2.model.entity.Prize;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrizeRepository extends JpaRepository<Prize, Long> {

    @Modifying
    @Query(value = """
            INSERT INTO prize(market_id, name, amount)
            SELECT m.market_id, :name, :amount
            FROM organizer o
            JOIN market m ON m.organizer_id = o.organizer_id
            WHERE o.gmail = :email
            LIMIT 1
            """, nativeQuery = true)
    void updateReward(String rewardName, int count, String email);

    @Modifying
    @Transactional
    @Query(value = """
            DELETE FROM prize 
                        WHERE market_id = (
                        SELECT m.market_id
                        FROM market m 
                        JOIN organizer o ON o.organizer_id = m.organizer_id 
                        WHERE o.gmail = :email
                        LIMIT 1
                        )""",
            nativeQuery = true)
    void deleteReward(String mail);

    @Query(value = """
            SELECT u.name AS userName, p.name AS rewardName
            FROM lottery l
            JOIN prize p ON p.prize_id = l.prize_id
            JOIN user u ON u.user_id = l.user_id
            WHERE p.market_id = (
                SELECT m.market_id
                FROM market m
                JOIN organizer o ON o.organizer_id = m.organizer_id
                WHERE o.gmail = :email
                LIMIT 1
            )
            """, nativeQuery = true)
    List<DrawResultDTO> findDrawResult(String email);
}
