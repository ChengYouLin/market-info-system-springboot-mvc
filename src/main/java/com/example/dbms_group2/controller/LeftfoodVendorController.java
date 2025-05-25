package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.*;
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
public class LeftfoodVendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping("/leftfood")
    public String showLeftoverPage(Model model, @SessionAttribute("account") String vendorId,
                                   RedirectAttributes redirectAttributes, HttpSession session) {

        Object user = session.getAttribute("account");
        Object role = session.getAttribute("role");
        if (!("v".equals(session.getAttribute("role")))) {
            redirectAttributes.addFlashAttribute("message", "您非攤商身份！");
            return "redirect:/eView";
        } else if (user == null) {
            redirectAttributes.addFlashAttribute("message", "您尚未登入！");
            return "redirect:/eView/login";
        } else {

            List<LeftoverDTO> leftovers = vendorService.findGetLeftoversByVendor(vendorId);
            List<ProductNewDTO> productNewList = vendorService.getFindAllProduct(vendorId);
            System.out.println(productNewList.size());
            //System.out.println(leftovers.size());
            //System.out.println(leftovers.get(0).getProductName());
            //System.out.println(productNewList.get(1).getName());

            //鈴鐺，傳自己的然後去抓
            List<AnnouncementDTO> notices = vendorService.getFindMarketForVendorAnnouncement((String) user);
            model.addAttribute("notices", notices);


            model.addAttribute("leftovers", leftovers);
            model.addAttribute("productList", productNewList);
            System.out.println(leftovers.size());
            //System.out.println(productNewList.size());
            //System.out.println(leftovers.get(0).getProductName());

            return "leftfoodVendor"; // Thymeleaf 檔案名稱
        }
    }

    @PostMapping("/leftfood/add")
    public String addLeftover(@RequestParam String productName,
                              @RequestParam int quantity,
                              @SessionAttribute("account") String vendorId,
                              RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "成功新增剩食！");
        vendorService.addLeftover(productName, quantity, vendorId);
        return "redirect:/eView/vendor/leftfood";
    }

    @PostMapping("/leftfood/increase")
    public String increaseLeftover(@RequestParam int leftoverId,
                                   RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "增加現場份數乙份！");
        vendorService.changeQuantity(leftoverId);
        return "redirect:/eView/vendor/leftfood";
    }

    @PostMapping("/leftfood/decrease")
    public String decreaseLeftover(@RequestParam int leftoverId,
                                   RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "增加現場份數乙份！");

        vendorService.changeQuantity(leftoverId);
        return "redirect:/eView/vendor/leftfood";
    }

    @PostMapping("/leftfood/delete")
    public String deleteLeftover(@RequestParam int leftoverId,
                                 RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "刪除該筆剩食資料成功！");
        vendorService.deleteLeftover(leftoverId);
        return "redirect:/eView/vendor/leftfood";
    }

    @PostMapping("/leftfood/complete")
    public String completeRecord(@RequestParam int recordId,
                                 RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "剩食發放完成，使用者完成取餐！");
        vendorService.completeRecord(recordId);
        return "redirect:/eView/vendor/leftfood";
    }
}
