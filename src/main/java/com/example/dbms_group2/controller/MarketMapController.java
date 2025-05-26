package com.example.dbms_group2.controller;


import com.example.dbms_group2.model.DTO.*;
import com.example.dbms_group2.model.entity.Announcement;
import com.example.dbms_group2.service.UserService;
import com.mysql.cj.Session;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/eView/market")
public class MarketMapController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/map")
    public String viewMarketMap(@PathVariable("id") int marketId,
                                @SessionAttribute(name = "account", required = false) String user,
                                Model model, HttpSession session,
                                RedirectAttributes redirectAttributes) {
        session.setAttribute("id", marketId);

        List<VendorViewDTO> vendorList = userService.getFindVendorView(user, marketId);

        //鈴鐺問題
        List<AnnouncementDTO> notices = userService.findMarketAnnouncement(marketId);
        model.addAttribute("notices", notices);

        model.addAttribute("marketId", marketId);
        model.addAttribute("vendorList", vendorList);

        List<ZoneDTO> zones = userService.getFindAllZone(marketId);

        for (ZoneDTO zone : zones) {
            for (ZoneVendorDTO vendor : zone.getVendors()) {
                System.out.println(vendor.getBoothCode());  // 確保顯示名稱有正確賦值
            }
        }

        model.addAttribute("zones", zones);

        model.addAttribute("userEmail", user);

        List<MapDownDTO> map = userService.getLastOne(marketId);

        model.addAttribute("zoneInfo", map);



        return "marketMap2";
    }

    @PostMapping("/{id}/map/filter")
    public String handleCategoryFilter(@RequestParam(name = "categories", required = false) List<String> categories,
                                       @PathVariable("id") int marketId, Model model) {
        model.addAttribute("marketId", marketId);


        // 把 cate=類別 串接起來
//        String query = categories.stream()
//                .map(c -> "cate=" + UriUtils.encode(c, StandardCharsets.UTF_8)) // 避免中文字出錯
//                .collect(Collectors.joining("&"));

        return "redirect:/eView/market/" + marketId + "/map/filter";
    }


    //用在另一個controller
    @GetMapping("/{id}/map/filter")
    public String showMap(@PathVariable("id") int marketId,
                          Model model,
                          @SessionAttribute(name = "account", required = false) String user,
                          RedirectAttributes redirectAttributes) {
        //鈴鐺問題
        List<AnnouncementDTO> notices = userService.findMarketAnnouncement(marketId);
        model.addAttribute("notices", notices);

        //int length = selectedCategories.size();

       // List<FilterVendorDTO> vendors = userService.getFindFilterVendor(user, marketId, selectedCategories, length);

        model.addAttribute("marketId", marketId);             // 頁面切換用
        model.addAttribute("filteredVendors", null);

        return "testMap3";
    }

//    @PostMapping("/{id}/map/toggleFavorite")
//    public String toggleFavorite(@PathVariable("id") int marketId,
//                                 @SessionAttribute(name = "account", required = false) String user,
//                                 @RequestParam int vendorId,
//                                 @RequestHeader(value = "referer", required = false) String referer,
//                                 RedirectAttributes redirectAttributes,
//                                 HttpSession session) {
//
//        if(true) return "redirect:/eView/market/" + marketId + "/map";
//
//        Object role = session.getAttribute("role");
//
//        if(user == null || !( "u".equals(session.getAttribute("role")))) {
//            redirectAttributes.addFlashAttribute("message", "您尚未登入或非一般使用者！無法進行收藏！");
//            return "redirect:/eView/login";
//        }else{
//            userService.getUpdatePrefer(user,marketId, vendorId);
//            redirectAttributes.addFlashAttribute("message", "收藏成功！");
//            return "redirect:/eView/market/" + marketId + "/map";
//        }
//
//    }

    @PostMapping("/{id}/map/submitRating")
    public String submitRating(@PathVariable("id") int marketId,
                               @RequestParam Integer score,
                               @SessionAttribute(name = "account", required = false) String user,
                               @RequestParam int vendorId,
                               @RequestHeader(value = "referer", required = false) String referer,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {

        if(true) return "redirect:/eView/market/" + marketId + "/map";

        Object role = session.getAttribute("role");

        if(user == null || !("u".equals(session.getAttribute("role")))) {
            redirectAttributes.addFlashAttribute("message", "您尚未登入或非一般使用者！無法進行收藏！");
            return "redirect:/eView/login";
        }else{
            boolean result = userService.checkFindResultRank(user, marketId, vendorId, score);
            if(result == true){
                redirectAttributes.addFlashAttribute("message", "無法評分！");
            }else{
                redirectAttributes.addFlashAttribute("message", "評分完成！");
            }
            return "redirect:/eView/market/" + marketId + "/map";
        }

    }


}
