package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.HavePoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HavePointsRespository extends JpaRepository<HavePoints, Integer> {

    // List<PointDTO> findTotalPoint(String email, int marketId);

    //void updateHavePoint(String email, int marketId);
}
