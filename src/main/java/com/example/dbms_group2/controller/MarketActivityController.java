package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.service.MarketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/eView/organizer")
public class MarketActivityController {

    @Autowired
    private MarketService marketService;

    @GetMapping("/activity")
    public String showLotteryPage(Model model,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        System.out.println("!!!!!!!!!!!!");

        Object user = session.getAttribute("account");
        Object role = session.getAttribute("role");
        System.out.println(role);
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "您尚未登入！");
            return "redirect:/eView/login";
        } else if (!("o".equals(session.getAttribute("role")))) {
            System.out.println("OOOOOOQ");
            redirectAttributes.addFlashAttribute("message", "您身份不符??????！");
            return "redirect:/eView";
        } else if (marketService.getFindStatus((String) user).get(0).isActivity() == true) {

            List<LotteryDTO> lottery = marketService.getLotterySetting((String) user);
            List<UserDTO> users = marketService.getAllParticipants((String) user);
            List<DrawResultDTO> results = marketService.performDraw((String)user);
            model.addAttribute("lottery", lottery.get(0));
            model.addAttribute("lotteryUsers", users);
            model.addAttribute("drawResults", results);

            return "marketActivity";
        }
        redirectAttributes.addFlashAttribute("message", "您沒有開通活動抽獎功能！");
        return "redirect:/eView/organizer/config";
    }

    @PostMapping("/activity/save")
    public String saveLotterySetting(@RequestParam String title,
                                     @RequestParam String rule,
                                     @RequestParam List<String> rewardNames,
                                     @RequestParam List<Integer> rewardCounts,
                                     Model model,
                                     HttpSession session) {

        Object user = session.getAttribute("account");
        marketService.getUpdateReward(rewardNames, rewardCounts,(String)user);
        marketService.getUpdateActivityInfo(title,rule,(String)user);

        return "redirect:/eView/organizer/activity";
    }

    @PostMapping("/activity/draw")
    public String drawLottery(Model model,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {

        Object user = session.getAttribute("account");
        List<DrawResultDTO> results = marketService.performDraw((String)user);


        if(results == null){

            //進行抽獎
            marketService.getDrawActivity((String)user);

        }else{
            redirectAttributes.addFlashAttribute("message", "無法重複進行抽獎，請確認畫面中的中獎名單！");
            model.addAttribute("drawResults", results);

        }
        return "redirect:/eView/organizer/activity";
    }

    @PostMapping("/lottery/announce")
    public String announceWinners() {
        return "redirect:/eView/organizer/activity";
    }



}
