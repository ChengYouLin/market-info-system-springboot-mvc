package com.example.dbms_group2.controller;

import com.example.dbms_group2.service.HomeService;
import com.example.dbms_group2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/eView/market")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/home")
    public String viewMarketHome(@PathVariable("id") int marketId, Model model) {
        // 查詢該市集資訊
        MarketDTO market = marketService.getMarketById(marketId);

        if (market == null) {
            // 市集不存在時可以導到錯誤頁或顯示提示
            model.addAttribute("message", "找不到該市集！");
            return "errorPage"; // 或跳回首頁、404頁
        }

        model.addAttribute("market", market); // 傳給 view
        return "marketHome"; // Thymeleaf 檔案，例如 templates/market/home.html
    }

    @GetMapping("/{id}/profile")
    public String viewProfile(@PathVariable("id") int marketId, Model model) {

    }




}
