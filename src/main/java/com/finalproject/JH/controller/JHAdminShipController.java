package com.finalproject.JH.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.JH.service.JHShipService;
import com.finalproject.entity.Ship;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class JHAdminShipController {

    final JHShipService shipService;

    @PostMapping(value = "/admin/shiplistdelete.do")
    public String shiplistdeletePOST(@RequestParam(name="srn") String srn){
        try {
            Ship ship = shipService.selectShip(srn);
            if(ship != null) {
                ship.setSname(null);
                ship.setType(null);
                ship.setShipweight(null);
                ship.setMaxcargo(null);
                ship.setState("운행정지");
                log.info("deleteship222=>{}",ship.toString() );
                shipService.deleteShip(ship);
            }
            return "redirect:/orca/admin/shiplist.do?page=1";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/admin/shiplist.do?page=1";
        }
    }

    @PostMapping(value = "/admin/shiplist.do")
    public String shiplistPOST(@ModelAttribute Ship ship){

        try {
            log.info("ship => {}", ship.toString());
            ship.setState("운행대기");
            shipService.insertShip(ship);
            return "redirect:/orca/admin/shiplist.do?page=1";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/admin/shiplist.do?page=1";
        }
    }

    @GetMapping(value = "/admin/shiplist.do")
    public String shiplistGET(Model model, @AuthenticationPrincipal User user,
            @RequestParam(name="page", defaultValue = "0", required = false) int page){
        try {
             if(page == 0){
                 return "redirect:/orca/admin/shiplist.do?page=1";
             }
            PageRequest pageRequest = PageRequest.of((page-1), 10);
            long total = shipService.totalShip();

            List<Ship> list = shipService.selectShipPage(pageRequest);
            model.addAttribute("list", list);
            model.addAttribute("pages", (total-1)/10+1);
            model.addAttribute("user", user);
            return "/JH/Orca_adminshiplist";
        } 
        catch (Exception e) {
            e.printStackTrace();
            return "/JH/Orca_adminshiplist";
        }
    }
}
