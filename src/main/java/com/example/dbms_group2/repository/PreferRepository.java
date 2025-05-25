package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.FaoDTO;
import com.example.dbms_group2.model.entity.Prefer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreferRepository extends JpaRepository<Prefer, Long> {

    @Query("""
            SELECT new com.example.dbms_group2.model.DTO.FaoDTO(a.name, m.name, v.vendorId)
                        FROM Prefer p
                        JOIN p.apply a 
                        JOIN a.market m 
                        JOIN a.vendor v 
                        JOIN p.user u 
                        WHERE u.gmail = :email""")
    List<FaoDTO> findGetFindUserFao(@Param("email") String email);


    void deleteByPreferId(int preferId);

    @Query(value = """
    SELECT 
        EXISTS (
            SELECT 1
            FROM prefer p 
            JOIN apply app ON app.apply_id = p.apply_id
            WHERE app.market_id = :marketId 
              AND app.vendor_id = :vendorId 
              AND user_id = (SELECT user_id FROM user WHERE gmail = :email)
              AND app.status = '已通過'
        ) AS cond
    """, nativeQuery = true)
    Boolean isUserPreferred(@Param("email") String email, @Param("marketId") int marketId, @Param("vendorId") int vendorId);

    @Modifying
    @Transactional
    @Query(value = """
    DELETE p FROM prefer p
    JOIN apply app ON app.apply_id = p.apply_id
    WHERE app.market_id = :marketId
      AND app.vendor_id = :vendorId
      AND p.user_id = (SELECT user_id FROM user WHERE gmail = :email)
      AND app.status = '已通過'
    """, nativeQuery = true)
    void isUserPreferredTrue(@Param("email") String email, @Param("marketId") int marketId, @Param("vendorId") int vendorId);

    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO prefer(user_id, apply_id)
    VALUES (
        (SELECT user_id FROM user WHERE gmail = :gmail),
        (SELECT apply_id FROM apply WHERE market_id = :marketId AND vendor_id = :vendorId)
    )
    """, nativeQuery = true)
    void isUserPreferredFalse(@Param("email") String email, @Param("marketId") int marketId, @Param("vendorId") int vendorId);
    //void updatePrefer(String email, int marketId, int vendorId);
}
