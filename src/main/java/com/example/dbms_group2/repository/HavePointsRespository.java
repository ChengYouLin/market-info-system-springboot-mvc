package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.PointDTO;
import com.example.dbms_group2.model.entity.HavePoints;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HavePointsRespository extends JpaRepository<HavePoints, Integer> {

    @Query(value =
            """
                    SELECT h.points 
                    FROM have_points h
                    JOIN user u ON h.user_id = u.user_id
                    JOIN market m ON h.market_id = m.market_id
                    WHERE u.gmail = :email AND m.market_id = :marketId
                    """, nativeQuery = true)
    List<PointDTO> findTotalPoint(String email, int marketId);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE have_points h
        JOIN user u ON h.user_id = u.user_id
        SET h.points = h.points + 1
        WHERE u.gmail = :email AND h.market_id = :marketId
        """, nativeQuery = true)
    void updateHavePoint(@Param("email") String email, @Param("marketId") int marketId);


}
