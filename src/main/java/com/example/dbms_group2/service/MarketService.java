package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.model.entity.User;
import com.example.dbms_group2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class MarketService {

    @Autowired
    private OtherService otherService;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private PrizeRepository prizeRepository;
    @Autowired
    private LotteryRepository lotteryRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;


    public List<VendorApplicationDTO> getFindAllMarketApplyStatus(String email){
        return null;
        //return applyRepository.findAllMarketApplyStatus(email);
    }

    public void approve(int id){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        // 生成5位英文字母
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(letters.length());
            code.append(letters.charAt(index));
        }

        // 生成5位數字（0-9）
        for (int i = 0; i < 5; i++) {
            int digit = random.nextInt(10); // 0~9
            code.append(digit);
        }

        applyRepository.approveStatus(id, code.toString());
    }

    public void reject(int id){
        applyRepository.rejectStatus(id);
    }

    public List<UserDTO>findGetOrganizerDetail(String email){
        return organizerRepository.findGetOrganizerDetail(email);
    }

    public void getFindUpdateOrganizerProfile(String oldemail, String name, String email){
        organizerRepository.findUpdateOrganizerProfile(oldemail, name, email);
    }

    public List<LotteryDTO> getLotterySetting(String email){
        //return null;
        return otherService.lotterySetting(email);
    }

    public List<UserDTO> getAllParticipants(String email){
        return marketRepository.allParticipants(email);
    }

    public void getUpdateReward( List<String> rewardNames, List<Integer> rewardCounts, String email ){

        int size = rewardNames.size();

        prizeRepository.deleteReward(email);
        for(int i = 0; i < size; i++){
            prizeRepository.updateReward(rewardNames.get(i), rewardCounts.get(i), email);
        }
    }

    public void getUpdateActivityInfo(String title, String rule, String email){
        marketRepository.updateActivityInfo(title, rule, email);
    }

    public List<DrawResultDTO> performDraw(String email){
        return prizeRepository.findDrawResult(email);
    }

    public void getDrawActivity(String email){

        int totalUser = 0;
        List<UserDTO> userDTOList = getAllParticipants(email);
        totalUser = userDTOList.size();

        int totalRewardCount = 0;
        //List<RewardDTO> rewardDTOList = null;
        List<RewardDTO> rewardDTOList = marketRepository.lotteryAllInfo(email);
        if(rewardDTOList != null){
            for(RewardDTO rewardDTO : rewardDTOList){
                totalRewardCount = totalRewardCount + rewardDTO.getCount();
            }

            Set<Integer> resultSet = new HashSet<>();
            Random rand = new Random();

            while (resultSet.size() < totalRewardCount) {
                int num = rand.nextInt(totalUser); // 0 到 total-1
                resultSet.add(num);            // 不重複自動處理
            }
            List<Integer> resultList = new ArrayList<>(resultSet);

            for(int i = 0; i < totalRewardCount ; i++ ){
                lotteryRepository.updateLotteryResult(userDTOList.get(resultList.get(i)).getGmail(), email, rewardDTOList.get(i).getName());
            }
        }

    }

    public boolean isCheckInAllowedToday(String mail){
        int success =  marketRepository.checkInAllowedToday(mail);
        if(success == 1){
            return true;
        }else{
            return false;
        }
    }

    public List<CateDTO> getAvailableCategories(String email){
        return marketRepository.availableCategories(email);
    }

    public List<VendorApproveDTO> getVendorsForMarket(String email){
        return marketRepository.vendorsForMarket(email);
    }

    public List<VendorApproveDTO> getAvailableVendors(List<VendorApproveDTO> all){

        List<VendorApproveDTO> result = new ArrayList<>();
        for(VendorApproveDTO vendorApproveDTO : all){
            if(vendorApproveDTO.getBoothCode() == null){
                result.add(vendorApproveDTO);
            }
        }
        return result;
    }

    public void assignVendorToBooth(int mapId, int vendorId, String cate, String email){
        marketRepository.setAssignVendorToBooth(mapId, vendorId, cate, email);
    }

    public void markAsCheckedIn(int vendorId, String email){
        marketRepository.setMarkAsCheckedIn(vendorId, email);
    }

    public List<MarketFormDTO> getMarketSettings(String mail){
        //return null;
        return otherService.marketSettings(mail);
    }


    public void saveMarketSettings(String marketName, String location, LocalDate recruitStartDate, LocalTime recruitStartTime, LocalDate recruitEndDate, LocalTime recruitEndTime, String email, String facebook, String instagram, String line, String website, String specialId) {
        otherService.updateSaveMarketSettings(marketName, location,recruitStartDate, recruitStartTime, recruitEndDate, recruitEndTime, email, facebook, instagram, line, website, specialId);
    }

    public void saveMarketSettingsPeriod(List<EventPeriodDTO> eventPeriodDTOS, String email){
        marketRepository.deleteMarketPeriod(email);

        for(EventPeriodDTO eventPeriodDTO : eventPeriodDTOS){
            marketRepository.updateSaveMarketSettingsPeriod(
                    eventPeriodDTO.getStartDate(),
                    eventPeriodDTO.getStartTime(),
                    eventPeriodDTO.getEndDate(),
                    eventPeriodDTO.getEndTime(),
                    email
            );
        }
    }

    public void createNotice(NoticeDTO noticeDTO, String email){
        announcementRepository.create(noticeDTO.getTitle(),
                noticeDTO.getContent(), noticeDTO.getTarget(), email);
    }

    public void updateNotice(NoticeDTO noticeDTO, String email){
        announcementRepository.update(noticeDTO.getId(), noticeDTO.getTitle(), noticeDTO.getContent(), noticeDTO.getTarget(), email);

    }
    public void deleteNotice(int id, String email){
        announcementRepository.delete(id);
    }
    public List<DTO> getFindStatus(String email){
        return organizerRepository.findTheStatus(email);
    }

    public List<NoticeDTO> getAllNotice(String email){
        return marketRepository.allNotice(email);
    }

    public List<VendorApplicationDTO> getFindAllApplys(String email){
        return otherService.findAllMarketApplyStatus(email);

    }
}
