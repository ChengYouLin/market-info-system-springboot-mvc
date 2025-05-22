package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {

    @Query("SELECT v FROM Vendor v WHERE v.gmail = :email")
    List<Vendor> findByGmail(@Param("email") String email);
}
