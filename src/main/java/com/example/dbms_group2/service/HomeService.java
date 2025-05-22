package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.MarketDTO;
import com.example.dbms_group2.repository.HomeQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeQueryRepository homeQueryRepository;


    public List<MarketDTO> activeMarkets(){
        return homeQueryRepository.findActivityMarket();
    }

    public List<MarketDTO> recruitingMarkets(){
        return homeQueryRepository.findRecruitingMarkets();
    }

}
