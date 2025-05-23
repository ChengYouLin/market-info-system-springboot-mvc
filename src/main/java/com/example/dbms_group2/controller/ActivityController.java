package com.example.dbms_group2.controller;

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
        }else if (session.getAttribute("user") == null || session.getAttribute("role") != "u") {
            redirectAttributes.addFlashAttribute("message", "尚未登入，無法參與抽獎！");
            return "redirect:/eView/login";
        }else{

            //鈴鐺的部分
            List<Announcement> notices = userService.findMarketAnnouncement(marketId);
            model.addAttribute("notices", notices);

            Object user = session.getAttribute("user");
            int total = userService.getFindTotalPoint((String) user, marketId);

            model.addAttribute("pointCount", total);
            model.addAttribute("activityRule", userService.getFindActDes(marketId));
        }
        return"activity";
    }

    @PostMapping("/{id}/activity/submitPoint")
    public String submitPoint(@PathVariable("id") int marketId,
                              @RequestParam("pointCode") String pointCode,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        Object user = session.getAttribute("account");

        //check 編號正確與否
        boolean success = userService.correctStamp(marketId, pointCode);
        //check 使用者的點數狀況
        int total = userService.getFindTotalPoint((String) user, marketId);

        if(total >= 10){
            redirectAttributes.addFlashAttribute("errorMsg", "您已經集滿全部點數！");
            return "redirect:/eView/market/" + marketId + "/activity";
        }

        if (success) {
            redirectAttributes.addFlashAttribute("successMsg", "點數已成功累積！");
            userService.findUpdateHavePoint((String) user, marketId);
        } else {
            redirectAttributes.addFlashAttribute("errorMsg", "無效或已使用的代碼！");
        }

        return "redirect:/eView/market/" + marketId + "/activity";
    }


}
