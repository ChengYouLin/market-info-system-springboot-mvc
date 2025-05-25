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
    private OtherService otherService;

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
    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private ApplyRepository applyRepository;

    public List<ProductNewDTO> getFindAllProduct(String mail){
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

    public void findUpdateUserProfile(String gmail, String name, String email){
        vendorRepository.updateVendorProfil(gmail, name, email);
    }

    public List<LeftoverDTO> findGetLeftoversByVendor(String vendorEmail){
        //return null;
        return otherService.getLeftoversByVendor(vendorEmail);
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

    public String getFindMarketName(int marketId){
        return marketRepository.findNameByMarketId(marketId);
    }

    public void getUpdateNewApply(int marketId, String name,
                                     String description,
                                     String email,
                                     String facebook,
                                     String instagram,
                                     String line,
                                     String website,
                                     String vendorEmail){
        applyRepository.updateNewApply(marketId, name, description, email, facebook, instagram,line,website,vendorEmail);
    }

    public List<MarketInfoDTO> getFindMarketList(String email){
        //return null;
        return marketRepository.findMarketList(email);
    }

    public List<VendorDetailDTO> getFindVendorList(String email){
        //return null;
        return otherService.findVendorList(email);
    }

    public List<QrSectionDTO> findGetStampInfo(String email){
        return applyRepository.getStampInfo(email);
    }

    public boolean getApplyStatus(String email, int marketId){
        int result = applyRepository.findGetApplyStatus(email, marketId);
        if(result == 1){
            return true;
        }else{
            return false;
        }
    }
}
