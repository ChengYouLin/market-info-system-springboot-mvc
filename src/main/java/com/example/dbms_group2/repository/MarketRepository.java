package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.model.entity.Market;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
                MAX(name) AS name,
                MAX(description) AS description,
                MAX(location) AS location,
                MAX(imageUrl) AS imageUrl,
                MAX(CASE WHEN rn = 1 THEN schedule_picture END) AS schedule1,
                MAX(CASE WHEN rn = 2 THEN schedule_picture END) AS schedule2,
                MAX(CASE WHEN rn = 3 THEN schedule_picture END) AS schedule3,
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


    String findNameByMarketId(int marketId);

    //List<MarketInfoDTO> findMarketList(String email);

    //List<VendorViewDTO> findVendorList(String email);

    //List<LotteryDTO> lotterySetting(String email);

    @Query(value = """
                SELECT u.name, u.gmail, u.telephone
                FROM user u
                JOIN have_points hp ON hp.user_id = u.user_id
                WHERE hp.points >= 10
                  AND hp.market_id IN (
                      SELECT m.market_id
                      FROM market m
                      JOIN organizer o ON o.organizer_id = m.organizer_id
                      WHERE o.gmail = :email
                  )
            """, nativeQuery = true)
    List<UserDTO> allParticipants(String email);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE market m 
                        JOIN organizer o ON o.organizer_id = m.organizer_id 
                        SET m.lottery_rule = :rule, m.lottery_title = :title 
                        WHERE o.gmail = :email"""
            , nativeQuery = true)
    void updateActivityInfo(String title, String rule, String email);

    //List<RewardDTO> lotteryAllInfo(String email);

    @Query(value =
            """
                    WITH t AS (
                      SELECT m.market_id AS id,
                             (DATE(NOW()) = o.date) AS open
                      FROM opening o
                      JOIN market m ON m.market_id = o.market_id
                      JOIN organizer org ON org.organizer_id = m.organizer_id
                      WHERE org.gmail = :email
                    )
                    SELECT MAX(open) AS max_open
                    FROM t
                    GROUP BY id
                    """, nativeQuery = true)
    boolean checkInAllowedToday(String email);

    @Query(value =
            """
                    SELECT z.name FROM zone z
                    JOIN organizer o ON o.organizer_id = z.organizer_id
                    WHERE o.gmail = :email
                    """
            , nativeQuery = true)
    List<CateDTO> availableCategories(String email);

    @Query(value = """
            SELECT 
              app.vendor_id AS id, 
              app.name, 
              v.gmail AS email, 
              ap.num AS boothCode, 
              CASE 
                WHEN c.status IS NOT NULL THEN c.status
                ELSE FALSE
              END AS checkedIn
            FROM apply app
            JOIN market m ON m.market_id = app.market_id
            JOIN vendor v ON v.vendor_id = app.vendor_id
            JOIN organizer org ON org.organizer_id = m.organizer_id
            LEFT JOIN `check` c ON c.apply_id = app.apply_id
            LEFT JOIN assignment_point ap ON ap.apply_id = app.apply_id
            WHERE app.status = '已通過'
              AND org.gmail = :email
            """, nativeQuery = true)
    List<VendorApproveDTO> vendorsForMarket(String email);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO assignment_point (apply_id, zone_id, num)
            SELECT
            app.apply_id,
            z.zone_id,
                    :mapId
            FROM apply app
            JOIN vendor v ON v.vendor_id = app.vendor_id
            JOIN market m ON m.market_id = app.market_id
            JOIN organizer o ON o.organizer_id = m.organizer_id
            JOIN zone z ON z.organizer_id = o.organizer_id AND z.name = :cate
                    WHERE
            v.vendor_id = :vendorId
            AND o.gmail = :marketEmail
            AND app.status = '已通過'
            LIMIT 1
            """, nativeQuery = true)
    void setAssignVendorToBooth(int mapId, int vendorId, String cate, String marketEmail);

    @Modifying
    @Transactional
    @Query(value = """
                UPDATE `check` c
                JOIN apply app ON app.apply_id = c.apply_id
                JOIN organizer o ON o.organizer_id = c.organizer_id
                SET c.status = TRUE
                WHERE o.gmail = :email
                AND app.vendor_id = :vendorId
            """, nativeQuery = true)
    void setMarkAsCheckedIn(int vendorId, String email);

    //List<MarketFormDTO> marketSettings(String mail);

//    void updateSaveMarketSettings(
//            String marketName,
//            String location,
//            LocalDate recruitStartDate,
//            LocalTime recruitStartTime,
//            LocalDate recruitEndDate,
//            LocalTime recruitEndTime,
//            String email,
//            String facebook,
//            String instagram,
//            String line,
//            String website,
//            String specialId
//    );

    @Modifying
    @Transactional
    @Query(value = """
            DELETE FROM opening
            WHERE market_id = (
                    SELECT mar.market_id
            FROM market mar
            JOIN organizer org ON mar.organizer_id = org.organizer_id
            WHERE org.gmail = :email
            LIMIT 1
                    )
            """, nativeQuery = true)
    void deleteMarketPeriod(String email);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO opening (market_id, date, start_time, end_time)
            VALUES (
                (SELECT mar.market_id
                            FROM market mar
                            JOIN organizer org ON mar.organizer_id = org.organizer_id
                            WHERE org.gmail = :email
                            LIMIT 1),
                :startDate,
                    :startTime,
                    :endTime
            )
            """, nativeQuery = true)
    void updateSaveMarketSettingsPeriod(
            LocalDate startDate,
            LocalTime startTime,
            LocalDate endDate,
            LocalTime endTime,
            String email
    );

    @Query(value = """
            SELECT * 
            FROM announcement an 
            WHERE an.organizer_id = (
                SELECT mar.organizer_id 
                FROM market mar 
                JOIN organizer org ON mar.organizer_id = org.organizer_id 
                WHERE org.gmail = :email 
                LIMIT 1
            )
            """, nativeQuery = true)
    List<NoticeDTO> allNotice(String email);
}
