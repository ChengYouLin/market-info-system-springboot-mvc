package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Long> {


    @Query(value = """
            WITH ranked_schedules AS (  SELECT 
                    s.*,
                    m.market_id,
                    m.name, m.description, m.location, m.imageUrl,
                    m.email AS gmail,
                    instagram, facebook, website, line,
                    ROW_NUMBER() OVER (PARTITION BY m.market_id ORDER BY s.schedule_id) AS rn
                FROM schedule s
                JOIN organizer o ON s.organizer_id = o.organizer_id
                JOIN market m ON o.organizer_id = m.organizer_id
            )
            SELECT 
                market_id,
                MAX(name) AS name,
                MAX(description) AS description,
                MAX(location) AS location,
                MAX(imageUrl) AS imageUrl,
                MAX(CASE WHEN rn = 1 THEN schedule_picture END) AS schedule_url_one,
                MAX(CASE WHEN rn = 2 THEN schedule_picture END) AS schedule_url_two,
                MAX(CASE WHEN rn = 3 THEN schedule_picture END) AS schedule_url_three,
                MAX(gmail) AS gmail,
                MAX(instagram) AS instagram,
                MAX(facebook) AS facebook,
                MAX(website) AS website,
                MAX(line) AS line
            FROM ranked_schedules
            WHERE market_id = 1
            GROUP BY market_id;"""
            , nativeQuery = true)
    List<MarketHomeDTO> findMarketInfo(@Param("marketId") int marketId);


    @Query("SELECT m.lotteryRule FROM Market m WHERE m.marketId = :id")
    String findLotteryRuleById(@Param("id") int id);

    String findMarketNameById(@Param("id") int id);

    List<MarketInfoDTO> findMarketList(String email);

    List<VendorViewDTO> findVendorList(String email);

    List<LotteryDTO> lotterySetting(String email);

    List<UserDTO> allParticipants(String email);

    void updateActivityInfo(String title, String rule, String email);

    List<RewardDTO> lotteryAllInfo(String email);

    boolean checkInAllowedToday(String email);

    List<CateDTO> availableCategories(String email);

    List<VendorApproveDTO> vendorsForMarket(String email);

    void setAssignVendorToBooth(int mapId, int vendorId, String cate, String marketEmail);

    void setMarkAsCheckedIn(int vendorId, String email);

    List<MarketFormDTO> marketSettings(String mail);

    void updateSaveMarketSettings(
            String marketName,
            String location,
            LocalDate recruitStartDate,
            LocalTime recruitStartTime,
            LocalDate recruitEndDate,
            LocalTime recruitEndTime,
            String email,
            String facebook,
            String instagram,
            String line,
            String website,
            String specialId
    );

    void deleteMarketPeriod(String email);

    void updateSaveMarketSettingsPeriod(
            LocalDate startDate,
            LocalTime startTime,
            LocalDate endDate,
            LocalTime endTime,
            String email
    );

    List<NoticeDTO> allNotice(String email);
}
