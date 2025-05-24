package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.AnnouncementDTO;
import com.example.dbms_group2.service.VendorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/eView/vendor")
public class VendorApplyController {

    @Autowired
    private VendorService vendorService;


    @GetMapping("/info/apply")
    public String vendorApply(@RequestParam("id") int marketId,Model model, HttpSession session, RedirectAttributes redirectAttributes) {


        Object user = session.getAttribute("account");
        Object role = session.getAttribute("role");
        if (role != "v") {
            redirectAttributes.addFlashAttribute("message", "您非攤商身份！");
            return "redirect:/eView";
        } else if (user == null) {
            redirectAttributes.addFlashAttribute("message", "您尚未登入！");
            return "redirect:/eView/login";
        } else {

            //鈴鐺，傳自己的然後去抓
            List<AnnouncementDTO> notices = vendorService.getFindMarketForVendorAnnouncement((String) user);
            model.addAttribute("notices", notices);

            String marketName = vendorService.getFindMarketName(marketId);

            model.addAttribute("marketName", marketName);

            return "vendorApply";

        }


    }


    @PostMapping("/info/apply")
    public String submitVendorApplication(@RequestParam("id") int marketId,
                                          @RequestParam("name") String name,
                                          @RequestParam("description") String description,
                                          @RequestParam("email") String email,
                                          @RequestParam("facebook") String facebook,
                                          @RequestParam("instagram") String instagram,
                                          @RequestParam("line") String line,
                                          @RequestParam("website") String website,
                                          @SessionAttribute("account") String vendorEmail,
                                          RedirectAttributes redirectAttributes, HttpSession session) {

            vendorService.getUpdateNewApply(marketId, name, description, email, facebook, instagram, line, website, vendorEmail);

            redirectAttributes.addFlashAttribute("message", "請查看「市集申請」中，是否為審核中狀態！");
            return "redirect:/eView/vendor/info/apply";
        }




    }


