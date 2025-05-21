package com.example.dbms_group2.controller;

import com.example.dbms_group2.service.HomeService;
import org.springframework.stereotype.Controller;

@Controller
public class EViewController {

    private HomeService homeService;

    @GetMapping("/eView")
    public String showHomePage(Model model) {
        List<homeService> activeMarkets = homeService.getActiveMarkets();
        List<homeService> recruitingMarkets = homeService.getRecruitingMarkets();

        model.addAttribute("activeMarkets", activeMarkets);
        model.addAttribute("recruitingMarkets", recruitingMarkets);

        return "eView";
    }

}
