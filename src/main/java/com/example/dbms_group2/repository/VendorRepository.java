package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.model.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {

    List<Vendor> findByGmail(String email);

    List<UserDTO> getVendorDetail(String email);

    void updateVendorProfil(String name, String email);
}
