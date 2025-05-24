package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.ZoneDTO;
import com.example.dbms_group2.model.DTO.ZoneVendorDTO;
import com.example.dbms_group2.model.DTO.ZonezoneDTO;
import com.example.dbms_group2.model.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {

    @Query(value = """
        SELECT DISTINCT z.code, z.name
        FROM assignment_point ap
        JOIN apply app ON ap.apply_id = app.apply_id
        JOIN zone z ON z.zone_id = ap.zone_id
        WHERE app.market_id = :marketId AND app.status = '已通過'
        """, nativeQuery = true)
    List<ZonezoneDTO> findAllZonezone(int marketId);

    @Query(value = """
        SELECT CONCAT(app.name, '（', z.name, '）') AS displayName, ap.num AS boothCode
        FROM assignment_point ap
        JOIN apply app ON ap.apply_id = app.apply_id
        JOIN zone z ON z.zone_id = ap.zone_id
        WHERE app.market_id = :marketId AND app.status = '已通過'""", nativeQuery = true)
    List<ZoneVendorDTO> findAllZoneVendor(int marketId);
}
