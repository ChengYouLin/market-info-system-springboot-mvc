package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.FaoDTO;
import com.example.dbms_group2.model.DTO.MarketHomeDTO;
import com.example.dbms_group2.model.DTO.TimeSlotDTO;
import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.model.entity.Announcement;
import com.example.dbms_group2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Autowired
    private PreferRepository preferRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private OpeningRepository openingRepository;

    public List<Announcement> findMarketAnnouncement(int marketId) {
        return userQueryRepository.findMarketAnnouncement(marketId);
    }

    public List<UserDTO> findGetUserDetail(String email) {
        return userQueryRepository.getUserDetail(email);
    }

    public List<FaoDTO> getFindUserFao(String email){
        return preferRepository.findGetFindUserFao(email);
    }

    public void deleteFao(int preferId){
        preferRepository.deleteById(preferId);
    }

    public void findUpdateUserProfile(String email, String name){
        userRepository.updateUserProfile(email,name);
    }

    public List<MarketHomeDTO> getFindMarketInfo(int marketId){
        return marketRepository.findMarketInfo(marketId);
    }

    public List<TimeSlotDTO> getFindTimeSlot(int marketId){
        return openingRepository.findTimeSlot(marketId);
    }

}
