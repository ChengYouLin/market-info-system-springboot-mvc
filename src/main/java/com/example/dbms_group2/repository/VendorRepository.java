package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.model.entity.Vendor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {


    @Query("SELECT v FROM Vendor v WHERE v.gmail = :gmail")
    List<Vendor> findByGmail(@Param("gmail") String gmail);


    @Query("""
            SELECT new com.example.dbms_group2.model.DTO.UserDTO(name, gmail, telephone) FROM Vendor
                        WHERE gmail = :email""")
    List<UserDTO> getVendorDetail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Vendor u SET u.gmail = :newEmail, u.name = :name WHERE u.gmail = :oldEmail")
    void updateVendorProfil(String oldEmail, String name, String newEmail);
}
