package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.ResultRankDTO;
import com.example.dbms_group2.model.entity.Rank;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankRepository extends JpaRepository<Rank, Long> {

    @Query(value = """
    SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END AS turn
    FROM `rank` r
    JOIN `user` u ON r.user_id = u.user_id
    JOIN apply a ON a.apply_id = r.apply_id
    WHERE u.gmail = :email
      AND a.market_id = :marketId
      AND a.vendor_id = :vendorId
      AND a.status = '已通過'
    """, nativeQuery = true)
    List<ResultRankDTO> findResultRank(String email, int markedId, int vendorId);

    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO `rank` (user_id, apply_id, rank_score)
    VALUES (
        (SELECT user_id FROM `user` WHERE gmail = :email),
        (SELECT a.apply_id
    FROM apply a
    WHERE a.vendor_id = :vendorId AND a.market_id = :marketId AND a.status = '已通過'),
            :score
    )
            """, nativeQuery = true)
    void updateRank(String email, int marketId, int vendorId ,int score);
}
