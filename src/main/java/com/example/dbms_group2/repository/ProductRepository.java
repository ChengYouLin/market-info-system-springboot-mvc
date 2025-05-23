package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.ProductDTO;
import com.example.dbms_group2.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
