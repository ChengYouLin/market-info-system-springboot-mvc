package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.QrSectionDTO;
import com.example.dbms_group2.model.DTO.StampDTO;
import com.example.dbms_group2.model.DTO.VendorApplicationDTO;
import com.example.dbms_group2.model.DTO.VendorViewDTO;
import com.example.dbms_group2.model.entity.Apply;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ApplyRepository extends JpaRepository<Apply, Long> {

    @Query(value = """
            SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END turn
            FROM apply a
            WHERE a.market_id IN (
                SELECT o.market_id FROM opening o WHERE DATE(o.date) = CURRENT_DATE AND o.market_id = :marketId
            )
            AND a.status = '已通過'
            AND a.stamp = :stamp
            """, nativeQuery = true)
    List<StampDTO> checkCorrectStamp(int marketId, String stamp);


    @Query(value = """
            SELECT
                t.boothId,
                t.boothCode,
                t.id,
                t.name,
                t.description,
                t.facebook,
                t.instagram,
                t.line,
                t.website,
                t.imageUrl,
                COALESCE(AVG(r.rank_score), 0) AS rating,
                CASE WHEN t.favorited IS NOT NULL THEN TRUE ELSE FALSE END AS favorited,
                t.zoneCode,
                t.zoneIndex,
                t.zoneName
            FROM (
                SELECT 
                    app.num AS boothId,
                    app.num AS boothCode,
                    v.vendor_id AS id,
                    ap.name,
                    ap.description,
                    ap.facebook,
                    ap.instagram,
                    ap.line,
                    ap.website,
                    m.imageUrl,
                    p.user_id AS favorited,
                    ap.apply_id,
                    z.code AS zoneCode,
                    z.code AS zoneIndex,
                    z.name AS zoneName
                FROM apply ap
                JOIN vendor v ON ap.vendor_id = v.vendor_id
                JOIN market m ON m.market_id = ap.market_id
                JOIN organizer o ON o.organizer_id = m.organizer_id
                JOIN assignment_point app ON app.apply_id = ap.apply_id
                JOIN zone z ON app.zone_id = z.zone_id
                LEFT JOIN prefer p ON p.apply_id = ap.apply_id AND p.user_id = (
                    SELECT user_id FROM user WHERE gmail = :gmail LIMIT 1
                )
                LEFT JOIN `rank` r ON r.apply_id = ap.apply_id
                WHERE ap.market_id = :marketId
                  AND ap.status = '已通過'
            ) t
            LEFT JOIN `rank` r ON r.apply_id = t.apply_id
            GROUP BY t.id, t.boothId, t.boothCode, t.name, t.description, t.facebook, t.instagram, t.line, t.website, t.imageUrl, t.favorited, t.zoneCode, t.zoneIndex, t.zoneName
            ORDER BY t.boothId ASC
            """, nativeQuery = true)
    List<VendorViewDTO> findVendorView(int marketId, String email);

    @Modifying
    @Transactional
    @Query(value =
            """
                    INSERT INTO apply (vendor_id, market_id, date, name, description, email, facebook, instagram, line, website, status)
                    VALUES ((SELECT vendor_id FROM vendor WHERE gmail = :vendorEmail LIMIT 1),:marketId, CURRENT_DATE, :name, :description, :email, :facebook, :instagram, :line, :website, '審核中')
                    """,
            nativeQuery = true)
    void updateNewApply(int marketId, String name, String description,
                        String email, String facebook, String instagram,
                        String line, String website, String vendorEmail);


    @Query("""
                SELECT new com.example.dbms_group2.model.DTO.QrSectionDTO(m.name, a.name, a.stamp)
                FROM Apply a
                JOIN a.market m
                WHERE a.vendor.vendorId = (
                    SELECT v.vendorId FROM Vendor v WHERE v.gmail = :vendorEmail
                )
                AND a.status = '已通過'
            """)
    List<QrSectionDTO> getStampInfo(String vendorEmail);

    //List<VendorApplicationDTO> findAllMarketApplyStatus(String organizerEmail);

    @Modifying
    @Query(value = """
            UPDATE apply SET status = '已通過', stamp = :stamp WHERE apply_id = :applyId"""
            , nativeQuery = true)
    void approveStatus(int applyId, String stamp);

    @Modifying
    @Query(value = """
UPDATE apply SET status = '未通過' WHERE apply_id = :applyId"""
            , nativeQuery = true)
    void rejectStatus(int applyId);
}
