package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.MarketDTO;
import com.example.dbms_group2.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public  interface HomeQueryRepository extends JpaRepository<Market, Long> {

    @Query("""
        SELECT new com.example.dbms_group2.model.DTO.MarketDTO(
            m.marketId,
            m.name,
            m.description,
            m.location,
            MIN(o.date),  
            MAX(o.date),  
            m.imageUrl
        )
        FROM Market m
        JOIN Opening o ON o.market = m
        WHERE CURRENT_TIMESTAMP >= m.recruitEndTime
        GROUP BY m.marketId, m.name, m.description, m.location, m.imageUrl
        HAVING CURRENT_TIMESTAMP <= MIN(o.date)
        ORDER BY MAX(o.date)
    """)
    List<MarketDTO> findActivityMarket();



    @Query("""
        SELECT new com.example.dbms_group2.model.DTO.MarketDTO(
            m.marketId,
            m.name,
            m.description,
            m.location,
            CAST(m.recruitStartTime AS LocalDate),
            CAST(m.recruitEndTime AS LocalDate),
            m.imageUrl
        )
        FROM Market m
        WHERE CURRENT_TIMESTAMP >= m.recruitStartTime
        AND CURRENT_TIMESTAMP <= m.recruitEndTime
        ORDER BY m.recruitEndTime
    """)
    List<MarketDTO> findRecruitingMarkets();
}
