package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.ResultRankDTO;
import com.example.dbms_group2.model.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankRepository extends JpaRepository<Rank, Long> {

    List<ResultRankDTO> findResultRank(String email, int markedId, int vendorId);

    void updateRank(String email, int marketId, int vendorId ,int score);
}
