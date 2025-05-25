package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.LeftoverDTO;
import com.example.dbms_group2.model.DTO.LeftoverFoodDTO;
import com.example.dbms_group2.model.entity.Leftover;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeftoverRepository extends JpaRepository<Leftover, Long> {

@Query(value = """
        SELECT
            l.name AS leftoverName, 
            a.name AS vendorApplyName, 
            l.type AS leftoverType,
            (l.amount - SUM(r.amount)) AS remaining, 
            l.leftover_id AS leftoverId,
                    1 AS test
        FROM apply a
        JOIN vendor v ON v.vendor_id = a.vendor_id
        JOIN leftover l ON v.vendor_id = l.vendor_id
        LEFT JOIN reserve r ON l.leftover_id = r.leftover_id
        WHERE l.vendor_id IN (
            SELECT vendor_id
            FROM apply sub_a
            WHERE market_id = :marketId AND status = '已通過'
        )
        GROUP BY
            l.leftover_id,
            l.name,
            l.type,
            l.amount,
            a.name
        HAVING remaining > 0
        ORDER BY l.leftover_id
        """, nativeQuery = true)
    List<LeftoverFoodDTO> findLeftInfo(@Param("marketId") int marketId);




    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO reserve (leftover_id, user_id, amount, last_time)
            SELECT
                    :leftoverId AS leftover_id,
            u.user_id,
                    :quantity AS amount,
            NOW() + INTERVAL 15 MINUTE
            FROM user u
            WHERE u.gmail = :user
            """, nativeQuery = true)
    void updateLeftFood(@Param("user") String user, @Param("leftoverId") int leftoverId, @Param("productName") String productName, @Param("quantity") int quantity);

    //List<LeftoverDTO> getLeftoversByVendor(String vendorEmail);

    @Modifying
    @Transactional
    @Query("""
            UPDATE Leftover l SET l.amount = l.amount + 1 WHERE l.leftoverId = :leftoverId""")
    void theIdQuantityPlusOne(int leftoverId);

    @Modifying
    @Transactional
    @Query("""
            UPDATE Leftover l SET l.amount = l.amount - 1 WHERE l.leftoverId = :leftoverId""")
    void theIdQuantityDecreOne(int leftoverId);

    @Modifying
    @Query(value = """
            DELETE r, l FROM leftover l LEFT JOIN reserve r ON r.leftover_id = l.leftover_id WHERE l.leftover_id = :leftoverId
            """, nativeQuery = true)
    void deleteThisLeftover(@Param("leftoverId") int leftoverId);


    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO leftover (vendor_id, name, amount, type, status)
            VALUES ((SELECT vendor_id FROM vendor WHERE gmail = :vendorEmail LIMIT 1), :productName, :quantity, (SELECT type FROM product p WHERE p.name = :productName LIMIT 1), :status)""",
            nativeQuery = true)
    void addNewLeftover(String productName, int quantity, String vendorEmail);
}
