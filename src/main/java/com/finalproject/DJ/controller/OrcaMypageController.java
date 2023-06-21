package com.finalproject.DJ.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.DJ.dto.Waitingview;
import com.finalproject.DJ.service.OrcaMypageService;
import com.finalproject.common.uuidnum;
import com.finalproject.entity.Estimate;
import com.finalproject.entity.OrderView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class OrcaMypageController {
    
    @Autowired OrcaMypageService mypageService;
    @Autowired uuidnum uuidnum;


    @GetMapping(value="/company/mypage.do")
    public String mypageGET(Principal principal,Model model,
                            @RequestParam(name = "menu",defaultValue = "0",required = false) int menu,
                            @RequestParam(name = "page",defaultValue = "0",required = false) int page
        ) {
        try {
            if(menu == 0 && page == 0){
                return "redirect:/orca/company/mypage.do?menu=1&page=1";
            }
            
            PageRequest pageRequest = PageRequest.of((page-1), 5);
            long total;

            if(menu == 1) {
                List<Waitingview> wlist = mypageService.WaitingViewTableList(principal.getName(),5*page-4,5*page);
                total = mypageService.CountWaitingView(principal.getName());
                log.info("waitingview=>{}", wlist);
                model.addAttribute("wlist", wlist);
                model.addAttribute("wpages", (total-1)/5+1);
            }
            else if(menu == 2) {
                List<Estimate> elist = mypageService.EstimateTableList(principal.getName(),pageRequest);
                // log.info("estimatelist=>{}",elist);
                long countState = mypageService.CountNoTransState("결제대기", principal.getName());
                log.info("mypage=>{}", countState);
                total = mypageService.CountEstimate(principal.getName());
                model.addAttribute("elist", elist);
                model.addAttribute("epages", (total-1)/5+1);
            }
            else if(menu == 3) {
                // List<LastorderView> o2list = mypageService.LastorderViewTableList(principal.getName(), pageRequest);
                // log.info("ordertable=>{}", o2list);
                List<OrderView> olist = mypageService.OrderViewTableList(principal.getName(), pageRequest);
                log.info("ordertable=>{}", olist);
                total = mypageService.CountOrderView(principal.getName());
                model.addAttribute("olist", olist);
                model.addAttribute("opages", (total-1)/5+1);
                // total = mypageService.CountOrderTable(principal.getName());
                // model.addAttribute("olist", olist);
                // model.addAttribute("opages", (total-1)/5+1);
            }

            long cargoCount = mypageService.CountWaitingView(principal.getName());
            log.info("mypage=>{}", cargoCount);
            model.addAttribute("cargoCount", cargoCount+" 건");

            long estimateCount = mypageService.CountEstimateState("대기", principal.getName());
            log.info("mypage=>{}", estimateCount);
            model.addAttribute("estimateCount", estimateCount+" 건");
            
            // long countState = mypageService.CountLastorderState("결제대기", principal.getName());
            // log.info("mypage=>{}", countState);
            long countState = mypageService.CountOrderViewState("결제대기", principal.getName());
            log.info("mypage=>{}", countState);
            model.addAttribute("countState", countState+" 건");

            model.addAttribute("user",mypageService.clientSelectOne(principal.getName()));
            
            log.info(Integer.toString(menu));
            return "/DJ/Orca_Mypage";    
        } catch (Exception e) {
            return "redirect:/orca/home.do";
        }
        
    }
}
