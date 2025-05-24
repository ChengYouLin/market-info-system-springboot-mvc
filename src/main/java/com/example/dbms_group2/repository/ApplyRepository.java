package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.StampDTO;
import com.example.dbms_group2.model.DTO.VendorViewDTO;
import com.example.dbms_group2.model.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApplyRepository extends JpaRepository<Apply, Long> {

//    list<StampDTO> checkCorrectStamp(int marketId, String stamp);
//
//    list<VendorViewDTO> findVendorView(int marketId, String email);
}
