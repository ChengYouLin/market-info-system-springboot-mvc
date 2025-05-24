package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.model.entity.User;
import com.example.dbms_group2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarketService {

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


    public List<VendorApplicationDTO> getFindAllMarketApplyStatus(String email){
        return applyRepository.findAllMarketApplyStatus(email);
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

    public void getFindUpdateOrganizerProfile(String name, String email){
        organizerRepository.findUpdateOrganizerProfile(name, email);
    }

    public List<LotteryDTO> getLotterySetting(String email){
        return marketRepository.lotterySetting(email);
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
        List<RewardDTO> rewardDTOList = marketRepository.lotteryAllInfo(email);
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
