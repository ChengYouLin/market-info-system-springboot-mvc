package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.DTO;
import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.model.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

    @Query("""
            SELECT new com.example.dbms_group2.model.DTO.DTO(m.organizer.lottery, m.organizer.leftover, m.organizer.product)
                        FROM Market m 
                        WHERE m.marketId = :marketId""")
    List<DTO> findStatus(int marketId);

    List<Organizer> findByGmail(String Email);

    @Query("""
            SELECT new com.example.dbms_group2.model.DTO.UserDTO(name, gmail, telephone) FROM Organizer 
                        WHERE gmail = :email""")
    List<UserDTO> findGetOrganizerDetail(String email);

    @Modifying
    @Query("""
            UPDATE Organizer o SET o.name = :name WHERE o.gmail = :email""")
    void findUpdateOrganizerProfile(String name, String email);

    @Query(value = """
            SELECT o.lottery, o.product, o.leftover FROM organizer o WHERE o.gmail = :email""", nativeQuery = true)
    List<DTO> findTheStatus(String email);

}
