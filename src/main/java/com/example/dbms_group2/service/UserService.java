package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.model.entity.Announcement;
import com.example.dbms_group2.model.entity.User;
import com.example.dbms_group2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private OtherService otherService;

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

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private AssignmentPointRepository assignmentPointRepository;

    public List<AnnouncementDTO> findMarketAnnouncement(int marketId) {
        return userQueryRepository.findMarketAnnouncement(marketId);
    }

    public List<UserDTO> findGetUserDetail(String email) {
        System.out.println("heer");
        System.out.println(userRepository.getUserDetail(email).get(0).getName());

        return userRepository.getUserDetail(email);
    }

    public List<FaoDTO> getFindUserFao(String email){
        return preferRepository.findGetFindUserFao(email);
    }

    public void deleteFao(int preferId){
        preferRepository.deleteByPreferId(preferId);
    }

    public void findUpdateUserProfile(String oldEmail, String name, String newEmail) {
        if (userRepository.existsOtherUserWithEmail(newEmail, oldEmail)) {
            System.out.println("<UNK>");

        }else{
            int updated = userRepository.updateUserGmailAndName(oldEmail, name, newEmail);
            System.out.println(">>>>>");

            if (updated == 0) {
                throw new IllegalArgumentException("使用者不存在或更新失敗");
            }
        }

    }



    public List<MarketHomeDTO> getFindMarketInfo(int marketId){
        return marketRepository.findMarketInfo(marketId);
    }

    public List<TimeSlotDTO> getFindTimeSlot(int marketId){
        return openingRepository.findTimeSlot(marketId);
    }

    public int getFindTotalPoint(String email, int marketId){
        List<PointDTO> p = havePointsRespository.findTotalPoint(email, marketId);
        System.out.println(p);
        System.out.println(email);
        System.out.println(marketId);
        int total = p.get(0).getPointCount();
        return total;
    }

    public List<DTO> genFindStatus(int marketId){
        return organizerRepository.findStatus(marketId);
    }

    public String getFindActDes(int marketId){
        return marketRepository.findLotteryRuleById(marketId);
    }

    public int correctStamp(int marketId, String stamp) {
        System.out.println(applyRepository.checkCorrectStamp(marketId, stamp));
        return applyRepository.checkCorrectStamp(marketId, stamp);
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

    public List<VendorViewDTO> getFindVendorView(String email, int marketId){
        return otherService.findVendorView(marketId, email);
    }

    public List<ZoneDTO> getFindAllZone(int marketId){
        //return null;
        return otherService.findAllZone(marketId);
    }

    public void getUpdatePrefer(String user, int marketId, int vendorId){
        otherService.updatePrefer(user, marketId , vendorId);
    }

    public boolean checkFindResultRank(String email, int marketId, int vendorId, int score){
        List<ResultRankDTO> result = rankRepository.findResultRank(email, marketId, vendorId);
        if(result.get(0).isResult()){
            return true;
        }else{
            rankRepository.updateRank(email, marketId, vendorId, score);
            return false;
        }
    }

    public List<FilterVendorDTO> getFindFilterVendor(String email, int marketId, List<String> selectedCategories, int length){
        //return null;
        return otherService.findFilterVendor(email, marketId, selectedCategories, length);
    }
}
