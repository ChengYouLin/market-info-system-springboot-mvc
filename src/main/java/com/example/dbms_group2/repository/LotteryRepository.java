package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.Lottery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LotteryRepository extends JpaRepository<Lottery, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO lottery (user_id, prize_id)
            SELECT u.user_id, p.prize_id
            FROM user u
            JOIN prize p ON 1=1
            JOIN market m ON m.market_id = p.market_id
            JOIN organizer o ON o.organizer_id = m.organizer_id
            WHERE u.gmail = :userEmail
            AND o.gmail = :marketEmail
            AND p.name = :prizeName
            """, nativeQuery = true)
    void updateLotteryResult(String userEmail, String marketEmail,
                             String prizeName);
}
