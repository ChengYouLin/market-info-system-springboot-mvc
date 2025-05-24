package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.ProductVendorDTO;
import com.example.dbms_group2.model.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = """
            SELECT 
                m.name AS marketName,
                a.update_time AS time,
                a.title AS title,
                a.content AS content
            FROM vendor v 
            JOIN apply ap ON ap.vendor_id = v.vendor_id
            JOIN market m ON ap.market_id = m.market_id
            JOIN organizer o ON o.organizer_id = m.organizer_id
            JOIN announcement a ON a.organizer_id = o.organizer_id
            WHERE v.gmail = :email
              AND ap.status = '已通過'
              AND a.target IN ('攤商')
            ORDER BY a.update_time DESC
            """, nativeQuery = true)
    List<ProductVendorDTO> findAllProduct(String vendorEmail);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.productId = :productId")
    void deleteProductByVendorId(int productId);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO product (vendor_id, name, type, price)
            SELECT v.vendor_id, :productName, :productCategory, :productPrice
            FROM vendor v
            WHERE v.gmail = :vendorEmail
            AND NOT EXISTS (
                    SELECT 1 FROM product p
                            WHERE p.vendor_id = v.vendor_id AND p.name = :productName
            )
            """, nativeQuery = true)
    void updateNewProduct(String vendorEmail, String productName, String productCategory, int productPrice);
}
