package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.service.VendorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/eView/vendor")
public class VendorInfoController {
    @Autowired
    private VendorService vendorService;

        @GetMapping("/info")
        public String showMainPage(Model model,
                HttpSession session, RedirectAttributes redirectAttributes) throws JsonProcessingException {

            Object user = session.getAttribute("account");
            Object role = session.getAttribute("role");

            if (!("v".equals(session.getAttribute("role")))){
                redirectAttributes.addFlashAttribute("message", "您非攤商身份！");
                return "redirect:/eView";
            } else if (user == null) {
                redirectAttributes.addFlashAttribute("message", "您尚未登入！");
                return "redirect:/eView/login";
            } else {

                //鈴鐺，傳自己的然後去抓
                List<AnnouncementDTO> notices = vendorService.getFindMarketForVendorAnnouncement((String) user);
                model.addAttribute("notices", notices);


                //市集申請紀錄
                List<MarketInfoDTO> marketList = vendorService.getFindMarketList((String) user);
                model.addAttribute("marketList", marketList);

                //
                List<VendorDetailDTO> vendorList = vendorService.getFindVendorList((String) user);
                String vendorMapJson = new ObjectMapper().writeValueAsString(vendorList);


                model.addAttribute("marketList", marketList);
                model.addAttribute("vendorMapJson", vendorMapJson);


                List<QrSectionDTO> qr =vendorService.findGetStampInfo((String) user);
                model.addAttribute("qrSection", null );
                model.addAttribute("qrList", qr );

                model.addAttribute("vendor", vendorList.get(0));
                model.addAttribute("vendorMapJson", vendorMapJson);

                return "vendorInfo";
            }
        }

    @GetMapping("/info/select")
    public String selectMarket(@RequestParam("marketName") String marketName,
                                Model model, HttpSession session, RedirectAttributes redirectAttributes){


        Object user = session.getAttribute("account");
        //鈴鐺，傳自己的然後去抓
        List<AnnouncementDTO> notices = vendorService.getFindMarketForVendorAnnouncement((String) user);
        model.addAttribute("notices", notices);


        List<MarketInfoDTO> marketList = vendorService.getFindMarketList((String) user);
        model.addAttribute("marketList", marketList);
        // 🔍 1. 查出該攤商參加的市集清單（for modal selector）
        List<QrSectionDTO> qrSections =vendorService.findGetStampInfo((String) user);

        model.addAttribute("qrList", qrSections );

        QrSectionDTO selectedSection = qrSections.stream()
                .filter(q -> q.getMarketName().equals(marketName))
                .findFirst()
                .orElse(null);

        model.addAttribute("qrSection", selectedSection);


        // ✅ 3. 攤商清單 JSON（modal 用）
        List<VendorDetailDTO> vendorList = vendorService.getFindVendorList((String) user);
        String vendorMapJson = "";
        try {
            vendorMapJson = new ObjectMapper().writeValueAsString(vendorList);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // 或 log.error(...)
            vendorMapJson = "[]"; // 預設為空陣列，避免 Thymeleaf 畫面錯誤
        }
        model.addAttribute("vendor", vendorList.get(0));

        model.addAttribute("vendorMapJson", vendorMapJson);


        return "vendorInfo"; // Thymeleaf 模板
    }





}
