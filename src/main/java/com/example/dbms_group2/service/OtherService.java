package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class OtherService {

    @Autowired
    private LeftoverRepository leftoverRepository;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private AssignmentPointRepository assignmentPointRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private PreferRepository zoneRepository;
    @Autowired
    private PreferRepository preferRepository;

    public OtherService() {
    }

    public boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");
    }

    public List<FilterVendorDTO> findFilterVendor(String email, int marketId, List<String> selectedCategories, int length){
        List<FilteredVendorInsideDTO> inside = assignmentPointRepository.findFilterVendorInside(email, marketId, selectedCategories, length);
        List<FilterVendorDTO> result = new ArrayList<>();

        for(int i = 0 ; i < inside.size() ; i++){
            FilterVendorDTO x = new FilterVendorDTO(inside.get(i).getCode(),
                    inside.get(i).getVendorId(),
                    inside.get(i).getName(),
                    assignmentPointRepository.findFilterVendorProduct(email, marketId, selectedCategories, length, inside.get(i).getVendorId()));
            result.add(x);
        }

        return result;

    }

    public List<ZoneDTO> findAllZone(int marketId) {
        List<ZoneDTO> result = new ArrayList<>();
        List<ZoneInsideDTO> inside = zoneRepository.findAllZonesInside(marketId);

        for(int i = 0 ; i < inside.size() ; i++){
            ZoneDTO x = new ZoneDTO(inside.get(i).getCode(),
                    inside.get(i).getName(),
                    zoneRepository.findAllZonesVendor(marketId, inside.get(i).getCode()));
            result.add(x);
        }

        return result;

    }


    public List<LeftoverDTO> getLeftoversByVendor(String vendorEmail) {
        List<LeftoverDTO> result = new ArrayList<>();
        List<LeftoverInsideDTO> inside = leftoverRepository.getLeftoversByVendorInside(vendorEmail);

        for(int i = 0 ; i < inside.size() ; i++){
            LeftoverDTO x = new LeftoverDTO(inside.get(i).getId(),
                    inside.get(i).getProductName(),
                    inside.get(i).getLeftCount(),
                    leftoverRepository.getLeftoversByVendorReserve(vendorEmail, inside.get(i).getId()));
            result.add(x);
        }

        return result;

    }

    public List<VendorApplicationDTO> findAllMarketApplyStatus(String organizerEmail) {
        List<VendorApplicationDTO> result = new ArrayList<>();
        List<VendorApplicationInsideDTO> inside = applyRepository.findAllMarketApplyStatusInside(organizerEmail);

        for(int i = 0 ; i < inside.size() ; i++){
            VendorApplicationDTO x = new VendorApplicationDTO(inside.get(i).getId(),
                    inside.get(i).getDate(),
                    inside.get(i).getEmail(),
                    inside.get(i).getName(),
                    inside.get(i).getStatus(),
                    inside.get(i).getBoothCode(),
                    inside.get(i).getDescription(),
                    inside.get(i).getFacebook(),
                    inside.get(i).getInstagram(),
                    inside.get(i).getLine(),
                    inside.get(i).getWebsite(),
                    applyRepository.findAllMarketApplyStatusProduct(organizerEmail, inside.get(i).getId()));
            result.add(x);
        }

        return result;

    }

    public List<LotteryDTO> lotterySetting(String email) {
        List<LotteryDTO> result = new ArrayList<>();
        List<LotteryInsideDTO> inside = marketRepository.lotterySettingInside(email);

        for(int i = 0 ; i < inside.size() ; i++){
            LotteryDTO x = new LotteryDTO(inside.get(i).getId(),
                    inside.get(i).getTitle(),
                    inside.get(i).getRule(),
                    marketRepository.lotteryAllInfo(email));
            result.add(x);
        }

        return result;

    }

    public List<MarketFormDTO> marketSettings(String mail){
        List<MarketFormDTO> result = new ArrayList<>();
        List<MarketFormInsideDTO> inside = marketRepository.marketSettingsInside(mail);

        for(int i = 0 ; i < inside.size() ; i++){
            MarketFormDTO x = new MarketFormDTO(inside.get(i).getMarketName(),
                    inside.get(i).getLocation(),
                    marketRepository.marketSettingsEvent(mail),
                    inside.get(i).getRecruitStartDate(),
                    inside.get(i).getRecruitStartTime(),
                    inside.get(i).getRecruitEndDate(),
                    inside.get(i).getRecruitEndTime(),
                    inside.get(i).getEmail(),
                    inside.get(i).getFacebook(),
                    inside.get(i).getInstagram(),
                    inside.get(i).getLine(),
                    inside.get(i).getWebsite());
            result.add(x);
        }

        return result;
    }

    public List<VendorDetailDTO> findVendorList(String email) {
        List<VendorDetailDTO> result = new ArrayList<>();
        List<VendorDetailInsideDTO> inside = marketRepository.findVendorListInside(email);

        for(int i = 0 ; i < inside.size() ; i++){
            VendorDetailDTO x = new VendorDetailDTO(inside.get(i).getTitle(),
                    inside.get(i).getDescription(),
                    marketRepository.findVendorListLink(email, inside.get(i).getApplyId()),
                    marketRepository.findVendorListProduct(email));
            result.add(x);
        }

        return result;
    }

    public void updatePrefer(String email, int marketId, int vendorId){
        Boolean cond = preferRepository.isUserPreferred(email, marketId, vendorId);
        if (cond){
            preferRepository.isUserPreferredTrue(email, marketId, vendorId);
        } else {
            preferRepository.isUserPreferredFalse(email, marketId, vendorId);
        }
    }


    public void updateSaveMarketSettings(
            String marketName,
            String location,
            LocalDate recruitStartDate,
            LocalTime recruitStartTime,
            LocalDate recruitEndDate,
            LocalTime recruitEndTime,
            String email,
            String facebook,
            String instagram,
            String line,
            String website,
            String specialId
    ){
        boolean cond = marketRepository.updateSaveMarketCond(marketName, location, recruitStartDate, recruitStartTime, recruitEndDate, recruitEndTime, email, facebook, instagram,
                line, website, specialId);
        if (cond){
            marketRepository.updateSaveMarketTrue(marketName, location, recruitStartDate, recruitStartTime, recruitEndDate, recruitEndTime, email, facebook, instagram,
                    line, website, specialId);
        } else {
            marketRepository.updateSaveMarketFalse(marketName, location, recruitStartDate, recruitStartTime, recruitEndDate, recruitEndTime, email, facebook, instagram,
                    line, website, specialId);
        }

    }
}
