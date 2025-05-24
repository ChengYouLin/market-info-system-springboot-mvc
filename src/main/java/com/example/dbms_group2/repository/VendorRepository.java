package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.model.entity.Vendor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    List<Vendor> findByGmail(String email);

    @Query("""
            SELECT new com.example.dbms_group2.model.DTO.UserDTO(name, gmail, telephone) FROM Vendor
                        WHERE gmail = :email""")
    List<UserDTO> getVendorDetail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Vendor v SET v.gmail = :email WHERE v.name = :name")
    void updateVendorProfil(String name, String email);
}
