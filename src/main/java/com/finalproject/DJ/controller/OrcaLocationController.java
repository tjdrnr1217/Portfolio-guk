package com.finalproject.DJ.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalproject.DJ.service.OrcaMypageService;

import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class OrcaLocationController {
    final HttpSession httpSession;
    @Autowired OrcaMypageService mypageService;

    @GetMapping(value = "/location.do")
    public String locationGET(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        // model.addAttribute("user", mypageService.clientSelectOne(principal.getName()));
        // model.addAttribute("otb", mypageService.)
        return "/DJ/Orca_Location";
    }
    
}
