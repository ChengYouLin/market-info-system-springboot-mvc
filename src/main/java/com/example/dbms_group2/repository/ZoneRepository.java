package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.ZoneDTO;
import com.example.dbms_group2.model.DTO.ZoneVendorDTO;
import com.example.dbms_group2.model.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {

    List<ZoneDTO> findAllZone(int marketId);

    //可能用不到
    //List<ZoneDTO> findAllZone(int marketId);
}
