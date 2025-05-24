package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.ZoneDTO;
import com.example.dbms_group2.model.DTO.ZoneInsideDTO;
import com.example.dbms_group2.model.DTO.ZoneVendorDTO;
import com.example.dbms_group2.model.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {

    @Query(value = """
    SELECT z.code, z.name 
    FROM zone z 
    WHERE z.organizer_id = (
        SELECT o.organizer_id 
        FROM market m 
        JOIN organizer o ON m.organizer_id = o.organizer_id 
        WHERE m.market_id = :marketId 
        LIMIT 1
    )
    """, nativeQuery = true)
    List<ZoneInsideDTO> findAllZonesInside(int marketId);

    @Query(value = """
    SELECT z.code, CONCAT(app.name, '（', z.name, '）') AS displayName, ap.num
    FROM assignment_point ap
    JOIN apply app ON ap.apply_id = app.apply_id
    JOIN zone z ON z.zone_id = ap.zone_id
    WHERE app.market_id = :marketId
      AND z.code = :code
      AND app.status = '已通過'
    """, nativeQuery = true)
    List<ZoneVendorDTO> findAllZonesVendor(int marketId, String code);

}
