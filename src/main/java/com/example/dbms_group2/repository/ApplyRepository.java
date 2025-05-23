package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.QrSectionDTO;
import com.example.dbms_group2.model.DTO.StampDTO;
import com.example.dbms_group2.model.DTO.VendorViewDTO;
import com.example.dbms_group2.model.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ApplyRepository extends JpaRepository<Apply, Long> {

    List<StampDTO> checkCorrectStamp(int marketId, String stamp);

    List<VendorViewDTO> findVendorView(int marketId, String email);

    void updateNewApply(int marketId, String name, String description,
                   String email, String facebook, String instagram,
                   String line, String website, String vendorEmail);

    List<QrSectionDTO> getStampInfo(String vendorEmail);
}
