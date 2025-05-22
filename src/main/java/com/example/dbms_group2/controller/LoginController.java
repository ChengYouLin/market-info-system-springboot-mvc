package com.example.dbms_group2.controller;

import com.example.dbms_group2.service.HomeService;
import com.example.dbms_group2.service.UserService;
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
public class LoginController {

    @Autowired
    private HomeService homeService;

    //先確定是否登入過，如果登入過就跳回主畫面
    @GetMapping("/login")
    public String showLoginPage(HttpSession session, RedirectAttributes redirectAttributes) {
        Object user = session.getAttribute("account"); // 你可以換成自己的登入 key，例如 "loginUser"
        if (user != null) {
            redirectAttributes.addFlashAttribute("message", "您已經登入！");
            return "redirect:/eView";
        }
        System.out.println("login畫面成功載入");

        return "login";
    }

    // 登入按鈕按下去
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("role") String role,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

        boolean success = homeService.validateUser(email, password, role);

        if (success) {
            session.setAttribute("account", email);
            switch(role){
                case "organizer":
                    session.setAttribute("role", "o");
                    break;
                case "user":
                    session.setAttribute("role", "u");
                    break;
                case "vendor":
                    session.setAttribute("role", "v");
                    break;
            }

            // 根據 role 導向不同頁面
            if(role.equals("organizer")) {
                return "redirect:/eView/organizer/config";
            }else{
                return "redirect:/eView";
            }
        }

        // 如果登入失敗，顯示錯誤訊息並重新導回登入頁面
        redirectAttributes.addFlashAttribute("message", "登入失敗，請確認帳號密碼是否錯誤！");
        return "redirect:/eView/login";
    }

}
