package com.example.dbms_group2.controller;

import com.example.dbms_group2.model.DTO.AnnouncementDTO;
import com.example.dbms_group2.model.DTO.ProductDTO;
import com.example.dbms_group2.model.DTO.ProductVendorDTO;
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
public class ProductController {

    @Autowired
    private VendorService vendorService;

    @GetMapping("/product")
    public String showProductPage(Model model,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {

        Object user = session.getAttribute("account");
        Object role = session.getAttribute("role");
        if (role != "v") {
            redirectAttributes.addFlashAttribute("message", "您非攤商身份！");
            return "redirect:/eView";
        } else if (user == null) {
            redirectAttributes.addFlashAttribute("message", "您尚未登入！");
            return "redirect:/eView/login";
        } else {

            //鈴鐺，傳自己的然後去抓
            List<AnnouncementDTO> notices = vendorService.getFindMarketForVendorAnnouncement((String) user);
            model.addAttribute("notices", notices);

            //傳入相關資料
            List<ProductVendorDTO> products = vendorService.getFindAllProduct((String) user);
            model.addAttribute("productList", products);

            return "product";

        }
    }

    @PostMapping("/product/upload")
    public String uploadProduct(@RequestParam("name") String name,
                                @RequestParam("category") String category,
                                @RequestParam("price") int price,
                                @SessionAttribute("vendorId") String vendorId,
                                RedirectAttributes redirectAttributes) {

        vendorService.getUpdateNewProduct( vendorId,  name,  category,  price);
        // 呼叫 service 儲存商品
        redirectAttributes.addFlashAttribute("message", "請確認新上架商品是否在畫面中！（商品名稱不能重複唷！）");

        // 導回商品上架頁面（可用 redirect 防止重新整理送出）
        return "redirect:/eView/vendor/product";
    }


    @PostMapping("/eView/vendor/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int productId,
                                @SessionAttribute("account") String vendorId,
                                RedirectAttributes redirectAttributes) {

        vendorService.getDeleteProduct(productId);
        // 刪除後導回商品上架頁面
        redirectAttributes.addFlashAttribute("message", "請確認畫面中是否刪除！");

        return "redirect:/eView/vendor/product";
    }


}