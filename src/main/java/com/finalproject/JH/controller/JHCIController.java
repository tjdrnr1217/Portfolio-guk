package com.finalproject.JH.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class JHCIController {
    
    @GetMapping(value = "/ci.do")
    public String locationGET(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "/JH/Orca_ci";
    }
}
