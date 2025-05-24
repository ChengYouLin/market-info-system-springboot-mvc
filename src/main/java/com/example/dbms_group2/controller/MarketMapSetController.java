package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.BoothAssignmentDTO;
import com.example.dbms_group2.model.DTO.CateDTO;
import com.example.dbms_group2.model.DTO.VendorApproveDTO;
import com.example.dbms_group2.service.MarketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/eView/organizer")
public class MarketMapSetController {

    @Autowired
    private MarketService marketService;

    @GetMapping("/map")
    public String showMap(Model model,
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

            //這是全部的清單，放在下面的列表
            List<VendorApproveDTO> vendorList = marketService.getVendorsForMarket((String) user);
            //這是地圖點擊後設定的清單，刪除已經被設定的
            List<VendorApproveDTO> availableVendors = marketService.getAvailableVendors(vendorList);
            //這是所有的分區種類
            List<CateDTO> categories = marketService.getAvailableCategories((String) user);

            model.addAttribute("todayDate", LocalDate.now().toString());
            model.addAttribute("vendorList", vendorList);
            model.addAttribute("availableVendors", availableVendors);
            model.addAttribute("categories", categories);
            //true等於可以報到
            model.addAttribute("canCheckIn", marketService.isCheckInAllowedToday((String) user));

            return "marketMapSet1"; // Thymeleaf HTML 檔名
        }
    }

    @PostMapping("/vendor/checkin")
    public String checkIn(@RequestParam int vendorId, HttpSession session, RedirectAttributes redirectAttributes) {
        Object user = session.getAttribute("account");

        marketService.markAsCheckedIn(vendorId, (String) user);
        redirectAttributes.addFlashAttribute("message", "報到成功！");
        return "redirect:/eView/organizer/map";
    }


    @PostMapping("/booth/update")
    public String updateBoothAssignment(@ModelAttribute BoothAssignmentDTO boothDto,
                                        HttpSession session,
                                        RedirectAttributes redirectAttributes) {
        Object user = session.getAttribute("account");
        marketService.assignVendorToBooth(boothDto.getBoothCode(), boothDto.getVendorId(), boothDto.getCategory(),(String)user );
        redirectAttributes.addFlashAttribute("message", "設定成功！");
        return "redirect:/eView/organizer/map";
    }

}
