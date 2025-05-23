package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.model.entity.Product;
import com.example.dbms_group2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserQueryRepository userQueryRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private LeftoverRepository leftoverRepository;
    @Autowired
    private ReserveRepository reserveRepository;
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

    public List<UserDTO> findGetVendorDetail(String email){
        return vendorRepository.getVendorDetail(email);
    }

    public void findUpdateUserProfile(String name, String email){
        vendorRepository.updateVendorProfil(name, email);
    }

    public List<LeftoverDTO> findGetLeftoversByVendor(String vendorEmail){
        return leftoverRepository.getLeftoversByVendor(vendorEmail);
    }

    public void changeQuantity(int leftfoodId){
        leftoverRepository.theIdQuantityPlusOne(leftfoodId);
    }

    public void decreaseLeftover(int leftfoodId){
        leftoverRepository.theIdQuantityDecreOne(leftfoodId);
    }

    public void deleteLeftover(int leftfoodId){
        leftoverRepository.deleteThisLeftover(leftfoodId);
    }

    public void completeRecord(int recordId){
        reserveRepository.deleteThisRecord(recordId);
    }

    public void addLeftover(String name, int quan, String vendorEmail){
        leftoverRepository.addNewLeftover(name, quan, vendorEmail);
    }
}
