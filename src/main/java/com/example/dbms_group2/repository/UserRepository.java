package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByGmail(String email);

    //List<UserDTO> getUserDetail(String email)

    //void updateUserProfile(String name, String email)
}
