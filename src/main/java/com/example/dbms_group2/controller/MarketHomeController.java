package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.AnnouncementDTO;
import com.example.dbms_group2.model.DTO.MarketHomeDTO;
import com.example.dbms_group2.model.DTO.TimeSlotDTO;
import com.example.dbms_group2.model.entity.Announcement;
import com.example.dbms_group2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/eView/market")
public class MarketHomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/home")
    public String viewMarketHome(@PathVariable("id") int marketId, Model model) {

        //鈴鐺的部分
        List<AnnouncementDTO> notices = userService.findMarketAnnouncement(marketId);
        model.addAttribute("notices", notices);

        List<MarketHomeDTO> marketInfo = userService.getFindMarketInfo(marketId);
        model.addAttribute("market", marketInfo);

        List<TimeSlotDTO> infoTime = userService.getFindTimeSlot(marketId);
        model.addAttribute("slot", infoTime);

        return "marketHome";
    }

}
