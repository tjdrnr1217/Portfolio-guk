package com.finalproject.JH.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.JH.dto.search;
import com.finalproject.JH.service.JHScheduleMapperService;
import com.finalproject.JH.service.JHScheduleService;
import com.finalproject.entity.Schedule;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class JHEstimate0Controller {

    final JHScheduleService scheduleService;
    final JHScheduleMapperService scheduleMapperService;

    @GetMapping(value = "/company/estimatepage.do")
    public String estimatetotalGET(Model model, @AuthenticationPrincipal User user,
                @ModelAttribute Schedule obj, @ModelAttribute search search,
                @RequestParam(name="page", defaultValue = "0", required = false) int page,
                @RequestParam(name="departureport", defaultValue = "", required = false) String departureport,
                @RequestParam(name="arrivalport", defaultValue = "", required = false) String arrivalport){
        try {
            if(page == 0){
                return "redirect:/orca/company/estimatepage.do?page=1&departureport=" + departureport + "&arrivalport=" +arrivalport; 
            }

            if(search.getDepartureport().equals("A")) {departureport = "부산항";} if(search.getDepartureport().equals("B")) {departureport = "인천항";}
            if(search.getDepartureport().equals("C")) {departureport = "속초항";} if(search.getDepartureport().equals("D")) {departureport = "목포항";} 
            if(search.getDepartureport().equals("E")) {departureport = "완도항";} if(search.getDepartureport().equals("F")) {departureport = "여수항";}
            if(search.getDepartureport().equals("G")) {departureport = "광양항";} if(search.getDepartureport().equals("H")) {departureport = "포항항";}
            if(search.getDepartureport().equals("I")) {departureport = "진해항";} if(search.getDepartureport().equals("J")) {departureport = "통영항";}
            if(search.getDepartureport().equals("K")) {departureport = "울산항";} if(search.getDepartureport().equals("L")) {departureport = "제주항";}
            if(search.getDepartureport().equals("M")) {departureport = "서귀포항";}

            if(search.getArrivalport().equals("A")) {arrivalport = "부산항";} if(search.getArrivalport().equals("B")) {arrivalport = "인천항";}
            if(search.getArrivalport().equals("C")) {arrivalport = "속초항";} if(search.getArrivalport().equals("D")) {arrivalport = "목포항";} 
            if(search.getArrivalport().equals("E")) {arrivalport = "완도항";} if(search.getArrivalport().equals("F")) {arrivalport = "여수항";}
            if(search.getArrivalport().equals("G")) {arrivalport = "광양항";} if(search.getArrivalport().equals("H")) {arrivalport = "포항항";}
            if(search.getArrivalport().equals("I")) {arrivalport = "진해항";} if(search.getArrivalport().equals("J")) {arrivalport = "통영항";}
            if(search.getArrivalport().equals("K")) {arrivalport = "울산항";} if(search.getArrivalport().equals("L")) {arrivalport = "제주항";}
            if(search.getArrivalport().equals("M")) {arrivalport = "서귀포항";}

            PageRequest pageRequest = PageRequest.of(search.getPage()-1, 12);

            List<Schedule> list = scheduleService.selectSchedulePage(pageRequest);
            long total = scheduleService.totalSchedule();

            if(!departureport.equals("") && arrivalport.equals("")) {
                list = scheduleService.selectFilterDepartureSchedulePage(departureport, pageRequest);
                total = scheduleService.totalFilterDapartureSchedule(departureport);

            }
            else if(!arrivalport.equals("") && departureport.equals("")){
                list = scheduleService.selectFilterArrivalSchedulePage(arrivalport, pageRequest);
                total = scheduleService.totalFilterArrivalSchedule(arrivalport);
            }

            else if(!departureport.equals("") && !arrivalport.equals("")){
                list = scheduleService.selectFilterTwoPage(departureport, arrivalport, pageRequest);
                com.finalproject.SG.dto.Schedule schedule = new com.finalproject.SG.dto.Schedule();
                schedule.setDepartureport(departureport);
                schedule.setArrivalport(arrivalport);
                total = scheduleMapperService.totalFilterTwoSchedule(schedule);
            }

            model.addAttribute("list", list);
            model.addAttribute("pages", (total-1)/12+1);
            model.addAttribute("search", search);
            model.addAttribute("user", user);
            return "/JH/Orca_estimatepage";
        } catch (Exception e) {
            e.printStackTrace();
            return "/JH/Orca_estimatepage";
        }
    }
    
}
