package com.example.dbms_group2.controller;

import com.example.dbms_group2.service.HomeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/eView")
public class RegisterController {
//
//    @Autowired
//    private HomeService homeService;
//
//
//    @GetMapping("/register")
//    public String showLoginPage(HttpSession session, RedirectAttributes redirectAttributes) {
//        Object user = session.getAttribute("account"); // 你可以換成自己的登入 key，例如 "loginUser"
//        if (user != null) {
//            redirectAttributes.addFlashAttribute("message", "您已經登入！");
//            return "redirect:/eView";
//        }
//        System.out.println("register畫面成功載入");
//
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String register(@RequestParam("name") String name,
//                           @RequestParam("email") String email,
//                           @RequestParam("phone") String phone,
//                           @RequestParam("password") String password,
//                           @RequestParam("role") String role,
//                           RedirectAttributes redirectAttributes) {
//
//        boolean success = homeService.register(name, email, phone, password, role);
//
//        if (success) {
//            redirectAttributes.addFlashAttribute("message", "註冊成功，請登入！");
//            return "redirect:/eView/login";
//        } else {
//            redirectAttributes.addFlashAttribute("message", "註冊失敗，信箱(帳號)可能已經存在");
//            return "redirect:/eView/register";
//        }
//    }
//
//

}
