package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Long> {

    //List<MarketHomeDTO> findMarketInfo(int marketId);

    @Query("SELECT m.lotteryRule FROM Market m WHERE m.marketId = :id")
    String findLotteryRuleById(@Param("id") int id);

    String findMarketNameById(@Param("id") int id);

    List<MarketInfoDTO> findMarketList(String email);

    List<VendorViewDTO> findVendorList(String email);

    List<LotteryDTO> lotterySetting(String email);

    List<UserDTO> allParticipants(String email);

    void updateActivityInfo(String title, String rule, String email);

    List<RewardDTO> lotteryAllInfo(String email);
}
