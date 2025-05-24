package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.PointDTO;
import com.example.dbms_group2.model.entity.HavePoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HavePointsRespository extends JpaRepository<HavePoints, Integer> {

    @Query("""
            SELECT new com.example.dbms_group2.model.DTO.PointDTO(h.points)
            FROM HavePoints h
            WHERE h.user.gmail = :email AND h.market.marketId = :marketId""")
    List<PointDTO> findTotalPoint(String email, int marketId);

    @Modifying
    @Query("""
            UPDATE HavePoints h SET h.points = h.points + 1
                      WHERE h.user.gmail = :gmail AND h.market.marketId = :marketId""")
    void updateHavePoint(@Param("gmail") String email, @Param("marketId") int marketId);

}
