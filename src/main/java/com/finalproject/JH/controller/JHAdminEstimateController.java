package com.finalproject.JH.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.DJ.repository.DJOrcaEstimateRepository;
import com.finalproject.JH.service.JHEstimateService;
import com.finalproject.entity.Estimate;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class JHAdminEstimateController {

    final JHEstimateService estimateService;

    @GetMapping(value = "/admin/estimatelist.do")
    public String estimatelistGET(Model model, @AuthenticationPrincipal User user,
                @RequestParam(name="page", defaultValue = "0", required = false) int page){

        if(page == 0){
            return "redirect:/orca/admin/estimatelist.do?page=1";
        }
        PageRequest pageRequest = PageRequest.of((page-1), 7);
        long total = estimateService.totalEstimate();
       
        List<Estimate> list = estimateService.adminEstimateListPage(pageRequest);
        model.addAttribute("list", list);
        model.addAttribute("pages", (total-1)/7+1);
        model.addAttribute("user", user);
        return "/JH/admin_estimatelist";
    }
    
}
