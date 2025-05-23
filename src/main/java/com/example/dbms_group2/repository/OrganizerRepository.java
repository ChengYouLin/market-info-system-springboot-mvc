package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.DTO;
import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.model.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizerRepository extends JpaRepository<Organizer,Long> {

    List<DTO> findStatus(int marketId);

    List<Organizer> findByGmail(String Email);

    List<UserDTO> findGetOrganizerDetail(String email);

    void findUpdateOrganizerProfile(String name, String email);

}
