package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.model.entity.Market;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
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
                market_id AS id,
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
            WHERE market_id = :marketId
            GROUP BY market_id;"""
            , nativeQuery = true)
    List<MarketHomeDTO> findMarketInfo(@Param("marketId") int marketId);


    @Query("SELECT m.lotteryRule FROM Market m WHERE m.marketId = :id")
    String findLotteryRuleById(@Param("id") int id);


    @Query("SELECT m.name FROM Market m WHERE m.marketId = :marketId")
    String findNameByMarketId(int marketId);


    @Query(value = """
            SELECT
        m.market_id AS id,
        m.name AS name,
        o.name AS org,
        CONCAT(DATE_FORMAT(MIN(op.date), '%Y/%m/%d'), ' - ', DATE_FORMAT(MAX(op.date), '%Y/%m/%d')) AS dateRange,
        IFNULL(a.name, '') AS boothName,
        a.apply_id AS applyId,
        CASE
            WHEN a.status IS NOT NULL THEN a.status
            WHEN NOW() BETWEEN m.recruit_start_time AND m.recruit_end_time THEN '招商中'
            ELSE NULL
        END AS statusText,
        CASE
            WHEN a.status IS NOT NULL THEN a.status
            WHEN NOW() BETWEEN m.recruit_start_time AND m.recruit_end_time THEN '招商中'
            ELSE NULL
        END AS statusCode
    FROM market m
    JOIN organizer o ON m.organizer_id = o.organizer_id
    JOIN opening op ON op.market_id = m.market_id
    JOIN apply a\s
        ON a.market_id = m.market_id\s
       AND a.vendor_id = (SELECT vendor_id FROM vendor WHERE gmail = :email LIMIT 1)
    GROUP BY m.market_id, m.name, a.name, a.apply_id, a.status, m.recruit_start_time, m.recruit_end_time """, nativeQuery = true)
    List<MarketInfoDTO> findMarketList(String email);

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
    Integer checkInAllowedToday(String email);

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
              END AS checkedIn,
              z.name
            FROM apply app
            JOIN market m ON m.market_id = app.market_id
            JOIN vendor v ON v.vendor_id = app.vendor_id
            JOIN organizer org ON org.organizer_id = m.organizer_id
            LEFT JOIN `check` c ON c.apply_id = app.apply_id
            LEFT JOIN assignment_point ap ON ap.apply_id = app.apply_id
            LEFT JOIN zone z ON z.zone_id = ap.zone_id
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
            Date startDate,
            Time startTime,
            Date endDate,
            Time endTime,
            String email
    );

    @Query(value = """
            SELECT an.announcement_id, an.title, an.content, an.target
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

    @Query(value = """
            SELECT m.market_id, m.lottery_title, m.lottery_rule
            FROM market m
            JOIN organizer o ON o.organizer_id = m.organizer_id
            WHERE o.gmail = :email
            """, nativeQuery = true)
    List<LotteryInsideDTO> lotterySettingInside(String email);

    @Query(value = """
            SELECT p.name, p.amount 
            FROM prize p
            WHERE p.market_id = (
                SELECT m.market_id 
                FROM organizer o 
                JOIN market m ON m.organizer_id = o.organizer_id 
                WHERE o.gmail = :email 
                LIMIT 1
            )
            """, nativeQuery = true)
    List<RewardDTO> lotteryAllInfo(String email);

    @Query(value = """
            SELECT m.name AS marketName,
                   m.location,
                   DATE(m.recruit_start_time) AS recruitStartDate,
                   TIME(m.recruit_start_time) AS recruitStartTime,
                   DATE(m.recruit_end_time) AS recruitEndDate,
                   TIME(m.recruit_end_time) AS recruitEndTime,
                   m.email,
                   m.facebook,
                   m.instagram,
                   m.line,
                   m.website
            FROM market m
            JOIN organizer o ON o.organizer_id = m.organizer_id
            WHERE o.gmail = :mail
            """, nativeQuery = true)
    List<MarketFormInsideDTO> marketSettingsInside(String mail);

    @Query(value = """
            SELECT o.date AS date,
                   o.start_time AS startTime,
                   o.end_time AS endTime
            FROM opening o
            LEFT JOIN market m ON o.market_id = m.market_id
            LEFT JOIN organizer org ON org.organizer_id = m.organizer_id
            WHERE org.gmail = :mail
            """, nativeQuery = true)
    List<EventPeriodDTO> marketSettingsEvent(String mail);

    @Query(value = """
            SELECT app.name AS name, app.description AS description, app.apply_id
            FROM apply app
            JOIN vendor v ON v.vendor_id = app.vendor_id
            WHERE v.gmail = :email
            """, nativeQuery = true)
    List<VendorDetailInsideDTO> findVendorListInside(String email);

    @Query(value = """
            SELECT 'Email' AS name, email AS url FROM apply WHERE apply_id = :applyId
            UNION ALL
            SELECT 'Facebook', facebook FROM apply WHERE apply_id = :applyId
            UNION ALL
            SELECT 'Instagram', instagram FROM apply WHERE apply_id = :applyId
            UNION ALL
            SELECT 'Line', line FROM apply WHERE apply_id = :applyId
            UNION ALL
            SELECT 'Website', website FROM apply WHERE apply_id = :applyId
            """, nativeQuery = true)
    List<LinkDTO> findVendorListLink(String email, Long applyId);

    @Query(value = """
            SELECT p.name AS name, p.type AS type, p.price AS price
            FROM product p
            JOIN vendor v ON v.vendor_id = p.vendor_id
            WHERE v.gmail = :email
            """, nativeQuery = true)
    List<ProductDTO> findVendorListProduct(String email);

    @Query(value = """
            SELECT 
                EXISTS (
                    SELECT 1
                    FROM market m
                    JOIN organizer o ON m.organizer_id = o.organizer_id
                    WHERE o.gmail = :specialId
                )
            """, nativeQuery = true)
    Integer updateSaveMarketCond(
            String marketName,
            String location,
            Date recruitStartDate,
            Date recruitEndDate,
            String email,
            String facebook,
            String instagram,
            String line,
            String website,
            String specialId
    );

    @Modifying
    @Transactional
    @Query(value = """
    UPDATE market m
    JOIN organizer o ON o.organizer_id = m.organizer_id
    SET 
        m.name = :marketName, 
        m.location = :location, 
        m.recruit_start_time = :recruitStart, 
        m.recruit_end_time = :recruitEnd, 
        m.email = :email, 
        m.facebook = :facebook, 
        m.instagram = :instagram, 
        m.line = :line, 
        m.website = :website 
    WHERE o.gmail = :specialId
    """, nativeQuery = true)
    int updateSaveMarketTrue(@Param("marketName") String marketName,
                             @Param("location") String location,
                             @Param("recruitStart") Timestamp recruitStart,
                             @Param("recruitEnd") Timestamp recruitEnd,
                             @Param("email") String email,
                             @Param("facebook") String facebook,
                             @Param("instagram") String instagram,
                             @Param("line") String line,
                             @Param("website") String website,
                             @Param("specialId") String specialId);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO market(
                name, location, recruit_start_time, recruit_end_time, 
                email, facebook, instagram, line, website, organizer_id
            )
            VALUES (
                :marketName,
                :location,
                :recruitStart,
                :recruitEnd,
                :email,
                :facebook,
                :instagram,
                :line,
                :website,
                (SELECT organizer_id FROM organizer WHERE gmail = :specialId LIMIT 1)
            )
            """, nativeQuery = true)
    void updateSaveMarketFalse(String marketName, String location,
                               Timestamp recruitStart,
                               Timestamp recruitEnd,
                               String email,
                               String facebook,
                               String instagram,
                               String line,
                               String website,
                               String specialId);

}
