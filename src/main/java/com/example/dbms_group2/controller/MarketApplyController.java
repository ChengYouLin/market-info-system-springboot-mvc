package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.VendorApplicationDTO;
import com.example.dbms_group2.service.MarketService;
import com.example.dbms_group2.service.VendorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/eView/organizer")
public class MarketApplyController {

    @Autowired
    private MarketService marketService;
    @Autowired
    private VendorService vendorService;

    @GetMapping("/review")
    public String viewApplications(Model model,@SessionAttribute("account") String vendorId,
        RedirectAttributes redirectAttributes, HttpSession session) {

            Object user = session.getAttribute("account");
            Object role = session.getAttribute("role");
            if (user == null) {
                redirectAttributes.addFlashAttribute("message", "您尚未登入！");
                return "redirect:/eView/login";
            } else if (role != "o") {
                redirectAttributes.addFlashAttribute("message", "您身份不符！");
                return "redirect:/eView";
            } else {
                List<VendorApplicationDTO> applications = marketService.getAllApplications();
                model.addAttribute("applications", applications);
                return "marketApply"; // Thymeleaf 對應的 HTML 名稱
            }
    }

    @PostMapping("/review/approve")
    public String approveApplication(@RequestParam("applicationId") int  applicationId,  RedirectAttributes redirectAttributes) {
        marketService.approve(applicationId); // 改變申請狀態
        redirectAttributes.addFlashAttribute("message", "請於表確認該申請狀態欄位是否變為已通過！");
        return "redirect:/eView/organizer/review/status";
    }
    @PostMapping("/review/reject")
    public String rejectApplication(@RequestParam("applicationId") int  applicationId,  RedirectAttributes redirectAttributes) {
        marketService.reject(applicationId); // 改變申請狀態
        redirectAttributes.addFlashAttribute("message", "請於表確認該申請狀態欄位是否變為未通過！");
        return "redirect:/eView/organizer/review/status";
    }


}
