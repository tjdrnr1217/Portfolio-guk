package com.finalproject.SG.controller;

import java.util.ArrayList;
import java.util.List;
import java.net.URLEncoder;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.SG.service.CargoService;
import com.finalproject.entity.Cargo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
@Slf4j

public class OrcaCargoController {

    final CargoService cService;

    @GetMapping(value = "/company/cargoorderlist.do")
    public String checkGET(Model model,
    @AuthenticationPrincipal User user,
    @RequestParam(name = "page", defaultValue = "1", required = false) int page,
    @RequestParam(name = "type", defaultValue = "0", required = false) String type,
    @RequestParam(name = "text", defaultValue = "0", required = false) String text){
        try {
            if (user != null) {
                model.addAttribute("user", user);
            }    
            if(page == 0){
                type = URLEncoder.encode(type, "UTF-8");// redirect 한글깨짐현상 해결
                text = URLEncoder.encode(text, "UTF-8");// redirect 한글깨짐현상 해결
                return "redirect:/orca/company/cargoorderlist.do?page=1&type="+type+"&text="+text;

            }
            long listsize=0;
            PageRequest pageRequest = PageRequest.of(page-1,5);
            List<Cargo> list = new ArrayList<>();
            if(text.equals("0") || type.equals("0")){
            long total = cService.total(user.getUsername());
            list = cService.selectlistCargo(user.getUsername(),pageRequest);
            model.addAttribute("pages", (total - 1) / 5+1);
            model.addAttribute("list", list);
            return "/SG/company/cargoorderlist";
            }     
            else{
            if(type.equals("cargotype")){
                listsize = cService.totaltype(user.getUsername(),text);
                list = cService.selectlisttype(user.getUsername(),text,pageRequest);
            }else if(type.equals("cargolocation")){
                listsize = cService.totallocation(user.getUsername(),text);
                list = cService.selectlistlocation(user.getUsername(),text,pageRequest);
            }
            model.addAttribute("list", list);
            model.addAttribute("pages", (listsize - 1) / 5+1);
            model.addAttribute("type", type);
            model.addAttribute("text", text);
        }
            
            return "/SG/company/cargoorderlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redriect:/orca/home.do";
        }
    }
}
