package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.MarketHomeDTO;
import com.example.dbms_group2.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {

    //List<MarketHomeDTO> findMarketInfo(int marketId);

}
