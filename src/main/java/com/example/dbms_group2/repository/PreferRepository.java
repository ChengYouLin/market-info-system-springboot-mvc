package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.FaoDTO;
import com.example.dbms_group2.model.entity.Prefer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreferRepository extends JpaRepository<Prefer, Long> {

    @Query("""
            SELECT new com.example.dbms_group2.model.DTO.FaoDTO(a.name, m.name, v.vendorId)
                        FROM Prefer p
                        JOIN p.apply a 
                        JOIN a.market m 
                        JOIN a.vendor v 
                        JOIN p.user u 
                        WHERE u.gmail = :email""")
    List<FaoDTO> findGetFindUserFao(@Param("email") String email);


    void deleteById(int preferId);

    void updatePrefer(String email, int marketId, int vendorId);
}
