package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.MarketDTO;
import com.example.dbms_group2.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EViewController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/eView")
    public String showHomePage(Model model) {
        List<MarketDTO> activeMarkets = homeService.activeMarkets();
        List<MarketDTO> recruitingMarkets = homeService.recruitingMarkets();
        model.addAttribute("activeMarkets", activeMarkets);
        System.out.println("activeMarkets: " + activeMarkets.get(0).getId());
        model.addAttribute("recruitingMarkets", recruitingMarkets);
        System.out.println("主畫面成功載入");
        return "eView";
    }



}
