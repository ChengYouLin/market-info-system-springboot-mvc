package com.example.dbms_group2.controller;


import com.example.dbms_group2.model.DTO.DTO;
import com.example.dbms_group2.model.DTO.LeftoverFoodDTO;
import com.example.dbms_group2.model.DTO.ReservationDTO;
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
public class LeftfoodController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/leftfood")
    public String showReservations(Model model, @PathVariable("id") int marketId,
                                   HttpSession session, RedirectAttributes redirectAttributes) {

        List<DTO> DTO = userService.genFindStatus(marketId);

        if(DTO.get(0).isActivity() == false){
            redirectAttributes.addFlashAttribute("message", "本活動無提供剩食服務！");
            return "redirect:/eView/market/" + marketId + "/home";
        }else{
            Object user = session.getAttribute("account");

            List<ReservationDTO> reservations = userService.findGetReserInfo((String) user, marketId);
            List<LeftoverFoodDTO> leftFoods = userService.getfindLeftInfo(marketId);

            model.addAttribute("reservations", reservations);
            model.addAttribute("leftFoods", leftFoods);
        }

        return "leftfood"; // Thymeleaf 頁面檔名
    }

    @PostMapping("/{id}/leftfood/book")
    public String bookLeftover(@RequestParam int leftoverId,
                               @RequestParam String productName,
                               @RequestParam int quantity,
                               @SessionAttribute("account") String user,
                               @PathVariable("id") int marketId,
                               RedirectAttributes redirectAttributes) {

        userService.getUpdateLeftFood(user, leftoverId, productName, quantity);

        redirectAttributes.addFlashAttribute("message", "預約成功！請查看畫面上方區塊編號！");
        return "redirect:/eView/market/" + marketId + "/leftfood";
    }




}
