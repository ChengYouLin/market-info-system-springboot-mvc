package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.Prefer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferRepository extends JpaRepository<Prefer, Long> {

    // List<FaoDTO> findGetFindUserFao(String email);
    void deleteById(int preferId);

    void updatePrefer(String email, int marketId, int vendorId);
}
