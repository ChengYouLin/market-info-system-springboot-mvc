package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.AnnouncementDTO;
import com.example.dbms_group2.model.DTO.DTO;
import com.example.dbms_group2.model.entity.Announcement;
import com.example.dbms_group2.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/eView/market")
public class ActivityController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/activity")
    public String home(@PathVariable("id") int marketId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        List<DTO> DTO = userService.genFindStatus(marketId);

        if(DTO.get(0).isActivity() == false){
            redirectAttributes.addFlashAttribute("message", "本活動無提供抽獎！");
            return "redirect:/eView/market/" + marketId + "/home";
        }else if (session.getAttribute("account") == null) {
            System.out.println("here!!!!!1");
            System.out.println();
            System.out.println(session.getAttribute("account") == null);


            redirectAttributes.addFlashAttribute("message", "尚未登入，無法參與抽獎！");
            return "redirect:/eView/login";
        }else if (!("u".equals(session.getAttribute("role")))){
            redirectAttributes.addFlashAttribute("message", "您非一般使用者，無法參與抽獎！");
            return "redirect:/eView";
        }else{

            //鈴鐺的部分
            List<AnnouncementDTO> notices = userService.findMarketAnnouncement(marketId);
            model.addAttribute("notices", notices);

            Object user = session.getAttribute("account");
            int total = userService.getFindTotalPoint((String) user, marketId);

            model.addAttribute("id", marketId);
            model.addAttribute("pointCount", total);
            model.addAttribute("activityRule", userService.getFindActDes(marketId));
            return"activity";
        }

    }

    @PostMapping("/{id}/activity/submitPoint")
    public String submitPoint(@PathVariable("id") int marketId,
                              @RequestParam("pointCode") String pointCode,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        System.out.println("he!");
        Object user = session.getAttribute("account");

        //check 編號正確與否
        int success = userService.correctStamp(marketId, pointCode);
        System.out.println(success);
        //check 使用者的點數狀況
        int total = userService.getFindTotalPoint((String) user, marketId);
        System.out.println(total);
        System.out.println("out!");
        if(total >= 10){
            redirectAttributes.addFlashAttribute("errorMsg", "代碼錯誤或已達上限！");
            return "redirect:/eView/market/" + marketId + "/activity";
        }

        if (success==1) {
            redirectAttributes.addFlashAttribute("successMsg", "點數已成功累積！");
            userService.findUpdateHavePoint((String) user, marketId);
        } else {
            redirectAttributes.addFlashAttribute("errorMsg", "無效或已使用的代碼！");
        }

        return "redirect:/eView/market/" + marketId + "/activity";
    }


}
