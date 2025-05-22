package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.MarketDTO;
import com.example.dbms_group2.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public  interface HomeQueryRepository extends JpaRepository<Market, Long> {


    //List<MarketDTO> findActivityMarket();

    //List<MarketDTO> findRecruitingMarkets();
}
