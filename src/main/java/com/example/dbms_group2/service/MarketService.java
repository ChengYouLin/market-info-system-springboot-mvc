package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.model.DTO.VendorApplicationDTO;
import com.example.dbms_group2.model.entity.User;
import com.example.dbms_group2.repository.ApplyRepository;
import com.example.dbms_group2.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private OrganizerRepository organizerRepository;
    public List<VendorApplicationDTO> getFindAllMarketApplyStatus(String email){
        return applyRepository.findAllMarketApplyStatus(email);
    }

    public void approve(int id){
        applyRepository.approveStatus(id);
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
}
