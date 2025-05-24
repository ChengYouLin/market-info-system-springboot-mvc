package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.FilterVendorDTO;
import com.example.dbms_group2.model.DTO.FilteredVendorInsideDTO;
import com.example.dbms_group2.model.entity.AssignmentPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentPointRepository extends JpaRepository<AssignmentPoint, Long> {

    @Query(value = """
    SELECT ap.num AS code, app.vendor_id, app.name 
    FROM assignment_point ap
    LEFT JOIN apply app ON app.apply_id = ap.apply_id
    WHERE app.vendor_id IN (
    SELECT p.vendor_id FROM product p
    WHERE p.vendor_id IN (
      SELECT ap.vendor_id
      FROM apply ap
      JOIN market m ON m.market_id = ap.market_id
      JOIN organizer o ON o.organizer_id = m.organizer_id
      WHERE o.gmail = :email
    )
    AND p.type IN :selectedCategories
    )
    AND app.status = '已通過'""", nativeQuery = true)
    List<FilteredVendorInsideDTO> findFilterVendorOutside(String email, int marketId, List<String> selectedCategories, int length);


    List<FilterVendorDTO> findFilterVendor(String email, int marketId, List<String> selectedCategories, int length);
    List<PorductDTO> findInside(String email, int marketId, List<String> selectedCategories, int length, int vendor_id)

}
