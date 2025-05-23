package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.AnnouncementDTO;
import com.example.dbms_group2.model.DTO.ProductDTO;
import com.example.dbms_group2.model.DTO.ProductVendorDTO;
import com.example.dbms_group2.model.entity.Product;
import com.example.dbms_group2.repository.ProductRepository;
import com.example.dbms_group2.repository.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserQueryRepository userQueryRepository;

    public List<ProductVendorDTO> getFindAllProduct(String mail){
        return productRepository.findAllProduct(mail);
    }

    public List<AnnouncementDTO> getFindMarketForVendorAnnouncement(String email){
        return userQueryRepository.findMarketForVendorAnnouncement(email);
    }

    public void getDeleteProduct(int id){
        productRepository.deleteProductByVendorId(id);
    }

    public void getUpdateNewProduct(String vendorId, String name, String category, int price){
        productRepository.updateNewProduct(vendorId, name, category, price);
    }

}
