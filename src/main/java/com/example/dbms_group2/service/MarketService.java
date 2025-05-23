package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.VendorApplicationDTO;
import com.example.dbms_group2.repository.ApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {

    @Autowired
    private ApplyRepository applyRepository;

    public List<VendorApplicationDTO> getFindAllMarketApplyStatus(String email){
        return applyRepository.findAllMarketApplyStatus(email);
    }

    public void approve(int id){
        applyRepository.approveStatus(id);
    }

    public void reject(int id){
        applyRepository.rejectStatus(id);
    }
}
