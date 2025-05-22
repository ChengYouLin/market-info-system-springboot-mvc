package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.FaoDTO;
import com.example.dbms_group2.model.DTO.UserDTO;
import com.example.dbms_group2.model.entity.Announcement;
import com.example.dbms_group2.service.HomeService;
import com.example.dbms_group2.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/eView/market")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/profile")
    public String viewProfile(@PathVariable("id") int marketId,
                              Model model,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        Object user = session.getAttribute("account");
        Object role = session.getAttribute("role");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "您尚未登入！");
            return "redirect:/eView/login";
        }else if (role == "v"){
            return "redirect:/eView/vendor/profile";
        }else if (role == "o"){
            return "redirect:/eView/organizer/profile";
        }

        //鈴鐺的部分
        List<Announcement> notices = userService.findMarketAnnouncement(marketId);
        model.addAttribute("notices", notices);

        //個人資訊
        List<UserDTO> users = userService.findGetUserDetail((String) user);
        model.addAttribute("users", users);

        //喜愛資訊
        List<FaoDTO> faos = userService.getFindUserFao((String) user);
        model.addAttribute("fav", faos);

        return "profile";
    }

    @PostMapping("/{id}/profile/unfavorite")
    public String unfavoriteVendor(@PathVariable("id") int marketId,
                                   @RequestParam int vendorId,
                                   Model model,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        userService.deleteFao(vendorId);
        return "redirect:/eView/market/" + marketId + "/profile";
    }

    @PostMapping("/{id}/profile/edit")
    public String editProfile(@PathVariable("id") int marketId,
                                @RequestParam("name") String name,
                              @RequestParam("email") String email,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        Object user = session.getAttribute("account");
        userService.findUpdateUserProfile(name, email);
        session.setAttribute("account", email);

        redirectAttributes.addFlashAttribute("successMessage", "個人資訊已成功更新！");
        return "redirect:/eView/market/" + marketId + "/profile";
    }






}
