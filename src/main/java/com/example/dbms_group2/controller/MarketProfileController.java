package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.AnnouncementDTO;
import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.service.MarketService;
import com.example.dbms_group2.service.VendorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/eView/organizer")
public class MarketProfileController {

    @Autowired
    private MarketService marketService;

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
        } else if (!("o".equals(session.getAttribute("role")))) {
            redirectAttributes.addFlashAttribute("message", "您身份不符！");
            return "redirect:/eView";
        } else {

            List<UserDTO> users = marketService.findGetOrganizerDetail((String) user);
            model.addAttribute("users", users);
            model.addAttribute("name", users.get(0).getName());
            model.addAttribute("email", users.get(0).getGmail());


            return "marketProfile";
        }
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam("name") String name,
                              @RequestParam("email") String email,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        Object user = session.getAttribute("account");
        try {
            marketService.getFindUpdateOrganizerProfile((String)user,name, email);

            session.setAttribute("account", "test");

            redirectAttributes.addFlashAttribute("message", "請重新登入！若信箱不符規定，請使用原始信箱。");
            return "redirect:/eView/vendor/profile";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", "請重新登入！若信箱不符規定，請使用原始信箱。");
            return "redirect:/eView/vendor/profile";
        }
    }
}
