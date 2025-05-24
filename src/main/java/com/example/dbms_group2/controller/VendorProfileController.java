package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.AnnouncementDTO;
import com.example.dbms_group2.model.DTO.UserDTO;
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
public class VendorProfileController {

    @Autowired
    private VendorService vendorService;

    @GetMapping("/profile")
    public String viewProfile(Model model,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        Object user = session.getAttribute("account");
        Object role = session.getAttribute("role");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "您尚未登入！");
            return "redirect:/eView/login";
        } else if (role != "v") {
            redirectAttributes.addFlashAttribute("message", "您非攤商身份！");
            return "redirect:/eView";
        } else {

            //鈴鐺，傳自己的然後去抓
            List<AnnouncementDTO> notices = vendorService.getFindMarketForVendorAnnouncement((String) user);
            model.addAttribute("notices", notices);


            List<UserDTO> users = vendorService.findGetVendorDetail((String) user);
            model.addAttribute("users", users);


            return "vendorProfile";
        }
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam("name") String name,
                              @RequestParam("email") String email,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        Object user = session.getAttribute("account");
        vendorService.findUpdateUserProfile(name, email);
        session.setAttribute("account", email);

        redirectAttributes.addFlashAttribute("successMessage", "個人資訊已成功更新！");
        return "redirect:/eView/vendor/profile";
    }
}
