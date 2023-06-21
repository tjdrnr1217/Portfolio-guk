package com.finalproject.DJ.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/orca")
public class OrcaHarborLoadingController {
    
    @GetMapping(value="harborloading.do")
    public String harborloadingGET(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "/DJ/Orca_HarboLoading";
    }
    
}
