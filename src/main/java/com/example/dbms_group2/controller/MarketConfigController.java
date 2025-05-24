package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.MarketDTO;
import com.example.dbms_group2.model.DTO.MarketFormDTO;
import com.example.dbms_group2.model.DTO.NoticeDTO;
import com.example.dbms_group2.service.MarketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/eView/organizer")
public class MarketConfigController {

    @Autowired
    private MarketService marketService;

    @GetMapping("/config")
    public String showMarketForm(Model model,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {

        Object user = session.getAttribute("account");
        Object role = session.getAttribute("role");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "您尚未登入！");
            return "redirect:/eView/login";
        } else if (role != "o") {
            redirectAttributes.addFlashAttribute("message", "您身份不符！");
            return "redirect:/eView";
        } else {

            List<NoticeDTO> notices = marketService.getAllNotice((String) user);
            model.addAttribute("notices", notices);
            List<MarketFormDTO> market = marketService.getMarketSettings((String) user);  // 從資料庫取得目前設定
            model.addAttribute("market", market.get(0));

            return "marketConfig"; // Thymeleaf 頁面名稱
        }
    }

    @PostMapping("/config/save")
    public String saveMarket(@ModelAttribute MarketFormDTO market,
                             HttpSession session) {
        Object user = session.getAttribute("account");

        marketService.saveMarketSettings(
                market.getMarketName(),
                market.getLocation(),
                market.getRecruitStartDate(),
                market.getRecruitStartTime(),
                market.getRecruitEndDate(),
                market.getRecruitEndTime(),
                market.getEmail(),
                market.getFacebook(),
                market.getInstagram(),
                market.getLine(),
                market.getWebsite(),
                (String) user);

        marketService.saveMarketSettingsPeriod(
                market.getEventPeriods(),
                (String) user
        );
        return "redirect:eView/organizer/config";
    }

    @PostMapping("/new")
    public String createNotice(@ModelAttribute NoticeDTO notice,
                               HttpSession session) {
        String mail = (String) session.getAttribute("account");
        marketService.createNotice(notice, mail);
        return "redirect:/eView/organizer/config"; // 或其他回首頁位置
    }

    @PostMapping("/edit")
    public String editNotice(@ModelAttribute NoticeDTO notice,
                             HttpSession session) {
        String mail = (String) session.getAttribute("account");
        marketService.updateNotice(notice, mail);
        return "redirect:/eView/organizer/config"; // 或其他回首頁位置
    }

    @PostMapping("/delete")
    public String deleteNotice(@RequestParam("id") int id, HttpSession session) {
        String mail = (String) session.getAttribute("account");
        marketService.deleteNotice(id, mail);
        return "redirect:/eView/organizer/config"; // 或其他回首頁位置
    }
}

