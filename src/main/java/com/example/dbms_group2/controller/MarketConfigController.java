package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.EventPeriodDTO;
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

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
        } else if (!("o".equals(session.getAttribute("role")))) {
            redirectAttributes.addFlashAttribute("message", "您身份不符！");
            return "redirect:/eView";
        } else {

            MarketFormDTO dto = new MarketFormDTO();
            if (dto.getEventPeriods() == null || dto.getEventPeriods().isEmpty()) {
                List<EventPeriodDTO> periods = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    periods.add(new EventPeriodDTO());  // 全部欄位為 null
                }
                dto.setEventPeriods(periods);
                while (dto.getEventPeriods().size() < 3) {
                    dto.getEventPeriods().add(new EventPeriodDTO());}
            }

            List<NoticeDTO> notices = marketService.getAllNotice((String) user);
            model.addAttribute("notices", notices);
            List<MarketFormDTO> market = marketService.getMarketSettings((String) user);  // 從資料庫取得目前設定
            model.addAttribute("market", new MarketFormDTO());
            return "marketConfig"; // Thymeleaf 頁面名稱
        }
    }

    @PostMapping("/config/save")
    public String saveMarket(
            @RequestParam("marketName") String marketName,
            @RequestParam("location") String location,

            @RequestParam("eventPeriods[0].startDate") String ep0StartDate,
            @RequestParam("eventPeriods[0].startTime") String ep0StartTime,
            @RequestParam("eventPeriods[0].endDate") String ep0EndDate,
            @RequestParam("eventPeriods[0].endTime") String ep0EndTime,

            @RequestParam("eventPeriods[1].startDate") String ep1StartDate,
            @RequestParam("eventPeriods[1].startTime") String ep1StartTime,
            @RequestParam("eventPeriods[1].endDate") String ep1EndDate,
            @RequestParam("eventPeriods[1].endTime") String ep1EndTime,

            @RequestParam("eventPeriods[2].startDate") String ep2StartDate,
            @RequestParam("eventPeriods[2].startTime") String ep2StartTime,
            @RequestParam("eventPeriods[2].endDate") String ep2EndDate,
            @RequestParam("eventPeriods[2].endTime") String ep2EndTime,

            @RequestParam("recruitStartDate") String recruitStartDate,
            @RequestParam("recruitStartTime") String recruitStartTime,
            @RequestParam("recruitEndDate") String recruitEndDate,
            @RequestParam("recruitEndTime") String recruitEndTime,

            @RequestParam("email") String email,
            @RequestParam("facebook") String facebook,
            @RequestParam("instagram") String instagram,
            @RequestParam("line") String line,
            @RequestParam("website") String website
    ) {
        // 1️⃣ 將字串轉成 java.sql.Date / java.sql.Time（若資料要存入資料庫）
        try {
            MarketFormDTO dto = new MarketFormDTO();
            dto.setMarketName(marketName);
            dto.setLocation(location);
            dto.setEmail(email);
            dto.setFacebook(facebook);
            dto.setInstagram(instagram);
            dto.setLine(line);
            dto.setWebsite(website);

            // 舉辦時間（建議抽成 List<EventPeriodDTO>）
            List<EventPeriodDTO> periods = new ArrayList<>();
            periods.add(new EventPeriodDTO(Date.valueOf(ep0StartDate), Time.valueOf(ep0StartTime)
                    , Time.valueOf(ep0EndTime)));
            periods.add(new EventPeriodDTO(Date.valueOf(ep1StartDate), Time.valueOf(ep1StartTime)
                    , Time.valueOf(ep1EndTime)));
            periods.add(new EventPeriodDTO(Date.valueOf(ep2StartDate), Time.valueOf(ep2StartTime)
                    , Time.valueOf(ep2EndTime)));
            dto.setEventPeriods(periods);

            dto.setRecruitStartDate(Date.valueOf(recruitStartDate));
            dto.setRecruitStartTime(Time.valueOf(recruitStartTime));
            dto.setRecruitEndDate(Date.valueOf(recruitEndDate));
            dto.setRecruitEndTime(Time.valueOf(recruitEndTime));

            // ⏎ 呼叫 service 儲存
            //marketService.updateSaveMarketSettings(dto);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "redirect:/eView/organizer/config";
        }

        return "redirect:/eView/organizer/config";
    }


    @PostMapping("/config/new")
    public String createNotice(@ModelAttribute NoticeDTO notice,
                               HttpSession session) {
        String mail = (String) session.getAttribute("account");
        marketService.createNotice(notice, mail);
        return "redirect:/eView/organizer/config"; // 或其他回首頁位置
    }

    @PostMapping("/config/edit")
    public String editNotice(@ModelAttribute NoticeDTO notice,
                             HttpSession session) {
        String mail = (String) session.getAttribute("account");
        marketService.updateNotice(notice, mail);
        return "redirect:/eView/organizer/config"; // 或其他回首頁位置
    }

    @PostMapping("/config/delete")
    public String deleteNotice( @RequestParam("id") Long id, HttpSession session) {
        String mail = (String) session.getAttribute("account");
        marketService.deleteNotice(id , mail);
        return "redirect:/eView/organizer/config"; // 或其他回首頁位置
    }
}

