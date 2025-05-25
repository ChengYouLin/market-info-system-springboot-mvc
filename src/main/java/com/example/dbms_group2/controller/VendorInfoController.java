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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                List<MarketInfoDTO> marketList = vendorService.getFindMarketList((String) user);

                model.addAttribute("marketList", marketList);

                List<VendorViewDTO> vendorDetails = vendorService.getFindVendorList((String) user);

                List<QrSectionDTO> qr =vendorService.findGetStampInfo((String) user);
                model.addAttribute("qrSection", qr);

                ObjectMapper mapper = new ObjectMapper();
                String vendorListJson = mapper.writeValueAsString(vendorDetails);

                return "vendorInfo";
            }
        }


    }
