package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.MarketHomeDTO;
import com.example.dbms_group2.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Long> {

    @Query(value = "WITH ranked_schedules AS ( " +
            "    SELECT \n" +
            "        s.*, \n" +
            "        m.market_id,\n" +
            "        m.name, m.description, m.location, m.imageUrl,\n" +
            "        m.email AS gmail,\n" +
            "        instagram, facebook, website, line,\n" +
            "        ROW_NUMBER() OVER (PARTITION BY m.market_id ORDER BY s.schedule_id) AS rn\n" +
            "    FROM schedule s\n" +
            "    JOIN organizer o ON s.organizer_id = o.organizer_id\n" +
            "    JOIN market m ON o.organizer_id = m.organizer_id\n" +
            ")\n" +
            "SELECT \n" +
            "    market_id,\n" +
            "    MAX(name) AS name,\n" +
            "    MAX(description) AS description,\n" +
            "    MAX(location) AS location,\n" +
            "    MAX(imageUrl) AS imageUrl,\n" +
            "    MAX(CASE WHEN rn = 1 THEN schedule_picture END) AS schedule_url_one,\n" +
            "    MAX(CASE WHEN rn = 2 THEN schedule_picture END) AS schedule_url_two,\n" +
            "    MAX(CASE WHEN rn = 3 THEN schedule_picture END) AS schedule_url_three,\n" +
            "    MAX(gmail) AS gmail,\n" +
            "    MAX(instagram) AS instagram,\n" +
            "    MAX(facebook) AS facebook,\n" +
            "    MAX(website) AS website,\n" +
            "    MAX(line) AS line\n" +
            "FROM ranked_schedules\n" +
            "WHERE market_id = 1\n" +
            "GROUP BY market_id;", nativeQuery = true)
    MarketHomeDTO findMarketHomeByMarketId(@Param("marketId") int marketId);

    List<MarketHomeDTO> findMarketInfo(int marketId);

    @Query("SELECT m.lotteryRule FROM Market m WHERE m.marketId = :id")
    String findLotteryRuleById(@Param("id") int id);
}
