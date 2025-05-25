package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.DTO.ProductDTO;
import com.example.dbms_group2.model.DTO.ProductNewDTO;
import com.example.dbms_group2.model.DTO.ProductVendorDTO;
import com.example.dbms_group2.model.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("""
    SELECT new com.example.dbms_group2.model.DTO.ProductNewDTO(p.name, p.type, p.price, p.productId)
    FROM Product p
    JOIN p.vendor v
    WHERE v.gmail = :vendorEmail
""")
    List<ProductNewDTO> findAllProduct(@Param("vendorEmail") String vendorEmail);



    @Modifying
    @Transactional
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
