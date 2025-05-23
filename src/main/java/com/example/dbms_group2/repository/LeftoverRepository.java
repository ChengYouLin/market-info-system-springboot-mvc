package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.LeftoverDTO;
import com.example.dbms_group2.model.DTO.LeftoverFoodDTO;
import com.example.dbms_group2.model.entity.Leftover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeftoverRepository extends JpaRepository<Leftover, Long> {

    List<LeftoverFoodDTO> findLeftInfo(int marketId);

    void updateLeftFood(String user, int leftoverId, String productName, int quantity);

    List<LeftoverDTO> getLeftoversByVendor (String vendorEmail);

    void theIdQuantityPlusOne(int leftoverId);

    void theIdQuantityDecreOne(int leftoverId);

    void deleteThisLeftover(int leftoverId);

    void addNewLeftover(String productName, int quantity, String vendorEmail);
}
