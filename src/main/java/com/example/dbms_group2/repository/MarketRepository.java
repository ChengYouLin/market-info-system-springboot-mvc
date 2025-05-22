package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.MarketHomeDTO;
import com.example.dbms_group2.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MarketRepository extends JpaRepository<Market, Long> {

    //List<MarketHomeDTO> findMarketInfo(int marketId);
    @Query("SELECT m.lotteryRule FROM Market m WHERE m.marketId = :id")
    String findLotteryRuleById(@Param("id") int id);
}
