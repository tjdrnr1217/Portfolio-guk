package com.finalproject.SG.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/orca")
public class HomeController {
    @GetMapping(value = { "/home.do"})
    public String homeGET(Model model, @AuthenticationPrincipal User user) {
        if (user != null) {
            model.addAttribute("user", user);
        }    
        return "orca_index";
    }

    @GetMapping(value = { "/admin/home.do"})
    public String adminhomeGET(Model model, @AuthenticationPrincipal User user) {
        if (user != null) {
            model.addAttribute("user", user);
        }    
        return "orca_adminindex";
    }

    @GetMapping(value = { "/login.do"})
    public String loginGET() {
       
        return "orca_login";
    }

    @GetMapping(value="/403page.do")
public String PageGET(){
    return "/403page";
}

@GetMapping(value = { "/history.do"})
public String companyGET(Model model, @AuthenticationPrincipal User user) {
    if (user != null) {
        model.addAttribute("user", user);
    }   
    return "/SG/history";
}

@GetMapping(value = { "/operation.do"})
public String adminGET(Model model, @AuthenticationPrincipal User user) {
    if (user != null) {
        model.addAttribute("user", user);
    }   
    return "/SG/operation";
}
}
