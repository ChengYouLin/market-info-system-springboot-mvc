package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByGmail(String email);
}
