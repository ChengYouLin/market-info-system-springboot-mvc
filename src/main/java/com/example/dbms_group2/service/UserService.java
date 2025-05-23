package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.*;
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

    @Autowired
    private HavePointsRespository havePointsRespository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private LeftoverRepository leftoverRepository;

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

    public int getFindTotalPoint(String email, int marketId){
        List<PointDTO> p = havePointsRespository.findTotalPoint(email, marketId);
        return p.getFirst().getPointCount();
    }

    public List<DTO> genFindStatus(int marketId){
        return organizerRepository.findStatus(marketId);
    }

    public String getFindActDes(int marketId){
        return marketRepository.findLotteryRuleById(marketId);
    }

    public boolean correctStamp(int marketId, String stamp) {
        return applyRepository.checkCorrectStamp.getFirst().getCorrect();
    }

    public void findUpdateHavePoint(String email, int marketId){
        havePointsRespository.updateHavePoint(email, marketId);
    }

    public List<ReservationDTO> findGetReserInfo(String email, int marketId){
        return reserveRepository.getReserInfo(email, marketId);
    }

    public List<LeftoverFoodDTO> getfindLeftInfo(int marketId){
        return leftoverRepository.findLeftInfo(marketId);
    }

    public void getUpdateLeftFood(String user, int leftoverId, String productName, int quantity){
        leftoverRepository.updateLeftFood( user, leftoverId, productName, quantity);
    }


}
