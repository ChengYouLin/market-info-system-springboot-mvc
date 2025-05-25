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
        if(user == "test"){
            session.setAttribute("account", null);
            redirectAttributes.addFlashAttribute("message", "請重新登入！若信箱不符規定，請使用原始信箱。");
            return "redirect:/eView/login";
        }
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "您尚未登入！");
            return "redirect:/eView/login";
        }else if (("u".equals(session.getAttribute("role")))){
            return "redirect:/eView";
        }else if (("o".equals(session.getAttribute("role")))){
            return "redirect:/eView/organizer/profile";
        }else{
            //鈴鐺，傳自己的然後去抓
            List<AnnouncementDTO> notices = vendorService.getFindMarketForVendorAnnouncement((String) user);
            model.addAttribute("notices", notices);


            List<UserDTO> users = vendorService.findGetVendorDetail((String) user);
            model.addAttribute("users", users);
            model.addAttribute("name", users.get(0).getName());
            model.addAttribute("phone", users.get(0).getPhone());
            model.addAttribute("email", users.get(0).getGmail());
            return "vendorProfile";
        }
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam("name") String name,
                              @RequestParam("email") String email,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        Object user = session.getAttribute("account");
        try {
            vendorService.findUpdateUserProfile((String)user,name, email);

            session.setAttribute("account", "test");

            redirectAttributes.addFlashAttribute("message", "請重新登入！若信箱不符規定，請使用原始信箱。");
            return "redirect:/eView/vendor/profile";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", "請重新登入！若信箱不符規定，請使用原始信箱。");
            return "redirect:/eView/vendor/profile";
        }
    }
}
