package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByGmail(String email);

    @Query("""
            SELECT new com.example.dbms_group2.model.DTO.UserDTO(name,gmail,telephone)
            FROM User
            WHERE gmail = :email""")
    List<UserDTO> getUserDetail(String email);

    @Query("""
            UPDATE User u SET u.gmail = :email WHERE u.name = :name
            """)
    void updateUserProfile(String name, String email);
}
