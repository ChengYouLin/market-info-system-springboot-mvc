package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.ProductVendorDTO;
import com.example.dbms_group2.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<ProductVendorDTO> findAllProduct(String vendorEmail);

    void deleteProductByVendorId(int productId);

    void updateNewProduct(String vendorEmail, String productName, String productCategory, int productPrice);
}
