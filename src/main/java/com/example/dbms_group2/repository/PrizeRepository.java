package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.DrawResultDTO;
import com.example.dbms_group2.model.DTO.RecordDTO;
import com.example.dbms_group2.model.DTO.RewardDTO;
import com.example.dbms_group2.model.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrizeRepository extends JpaRepository<Prize, Long> {

    void updateReward(String rewardName, int count, String email);

    void deleteReward(String rewardName);

    List<DrawResultDTO> findDrawResult(String email);
}
