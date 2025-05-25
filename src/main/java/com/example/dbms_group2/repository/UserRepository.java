package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByGmail(String email);

    @Query("""
            SELECT new com.example.dbms_group2.model.DTO.UserDTO(name,gmail,telephone)
            FROM User
            WHERE gmail = :email""")
    List<UserDTO> getUserDetail(String email);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.gmail = :newEmail AND u.gmail != :oldEmail")
    boolean existsOtherUserWithEmail(String newEmail, String oldEmail);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.gmail = :newEmail, u.name = :name WHERE u.gmail = :oldEmail")
    int updateUserGmailAndName(String oldEmail, String name, String newEmail);

}
