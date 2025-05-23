package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.FilterVendorDTO;
import com.example.dbms_group2.model.entity.AssignmentPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentPointRepository extends JpaRepository<AssignmentPoint, Long> {


    List<FilterVendorDTO> findFilterVendor(String email, int marketId, List<String> selectedCategories, int length);
}
