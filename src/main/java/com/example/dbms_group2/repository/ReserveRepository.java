package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.LeftoverFoodDTO;
import com.example.dbms_group2.model.DTO.ReservationDTO;
import com.example.dbms_group2.model.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    @Query("""
                SELECT r.reserveId, l.name, r.lastTime, ap.name, l.amount
                FROM Reserve r
                JOIN r.leftover l
                JOIN l.vendor v
                JOIN Apply ap ON ap.vendor.vendorId = v.vendorId
                WHERE ap.market.marketId = :marketId
                  AND r.user.userId = (
                      SELECT u.userId FROM User u WHERE u.gmail = :email
                  )
                  AND ap.status = '已通過'
            """)
    List<ReservationDTO> getReserInfo(String email, int marketId);

    List<LeftoverFoodDTO> getLeftoverInfo(String email);

    @Modifying
    @Query("""
            DELETE FROM Reserve r WHERE r.reserveId = :recordId
            """)
    void deleteThisRecord(int recordId);
}
