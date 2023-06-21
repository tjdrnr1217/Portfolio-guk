package com.finalproject.SG.controller;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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

import com.finalproject.DJ.repository.DJOrcaCargoRepository;
import com.finalproject.DJ.repository.DJOrcaEstimateRepository;
import com.finalproject.DJ.repository.DJOrcaOrderTableRepository;
import com.finalproject.DJ.repository.DJOrcaScheduleRepository;
import com.finalproject.SG.service.CarService;
import com.finalproject.SG.service.ScheduleService;
import com.finalproject.SG.service.ShipService;
import com.finalproject.entity.Cargo;
import com.finalproject.entity.Estimate;
import com.finalproject.entity.Landtransportation;
import com.finalproject.entity.Ordertable;
import com.finalproject.entity.Schedule;
import com.finalproject.entity.Ship;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/orca")
public class OrcaadminController {

    final ScheduleService sService;
    final HttpSession httpSession;
    final ShipService shipService;
    final CarService carService;

    // 1. 운행등록 추가
    @GetMapping(value = "/admin/schedule.do")
    public String scheduleGET(
            Model model,
            @ModelAttribute Schedule obj,
            @AuthenticationPrincipal User user,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page) {
        try {
            if (page == 0) {
                return "redirect:/orca/admin/schedule.do?page=1";
            }

            PageRequest pageRequest = PageRequest.of((page - 1), 10);
            long total = sService.total();

            List<Schedule> list = sService.selectlistSchedule(pageRequest);
        
            model.addAttribute("list", list);
            model.addAttribute("pages", (total-1)/10+1);
            model.addAttribute("user", user);
            return "/SG/admin/adminschedule";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/home.do";
        }
    }

    @PostMapping(value = "/admin/schedule.do")
    public String schedulePOST(@ModelAttribute Schedule obj) {

        int ret = sService.insertSchedule(obj);
        System.out.println(ret);
        if (ret == 1) {
            return "redirect:/orca/admin/schedule.do";
        }
        return "redirect:/orca/admin/schedule.do";
    }

    // 2.운행등록 일괄수정

    // 1
    @PostMapping(value = "/admin/scheduleupdate.do")
    public String scheduleupdatePOST(@RequestParam(name = "chk[]") List<BigInteger> chk) {
        try {
            log.info("{}",chk);
            httpSession.setAttribute("chk[]", chk);
            return "redirect:/orca/admin/scheduleupdate.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/home.do";
        }
    }

    // 2.
    @SuppressWarnings("unchecked")
    @GetMapping(value = "/admin/scheduleupdate.do")
    public String scheduleupdateGET(Model model, @AuthenticationPrincipal User user) {
        try {
            List<BigInteger> chk = (List<BigInteger>) httpSession.getAttribute("chk[]");
            List<Schedule> list = sService.updateselectlistSchedule(chk);
            model.addAttribute("list", list);
            model.addAttribute("user", user);
            return "/SG/admin/adminscheduleupdate";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/home.do";
        }
    }

    // 3.
    @PostMapping(value = "/admin/scheduleupdateaction.do")
    public String updatePOST(
            @RequestParam(name = "scheduleno[]") BigInteger[] scheduleno,
            @RequestParam(name = "port.portcode[]") String[] portcode,
            @RequestParam(name = "departureport[]") String[] departureport,
            @RequestParam(name = "arrivalport[]") String[] arrivalport,
            @RequestParam(name = "departuredate[]") String[] departuredate,
            @RequestParam(name = "arrivaldate[]") String[] arrivaldate) {
        try {
            List<Schedule> list = new ArrayList<>();
            for (int i = 0; i < scheduleno.length; i++) {
                Schedule obj = sService.updatelistSchedule(scheduleno[i]);
                log.info("{}", obj.toString());
                obj.setDepartureport(departureport[i]);
                obj.setArrivalport(arrivalport[i]);
                obj.setDeparturedate(departuredate[i]);
                obj.setArrivaldate(arrivaldate[i]);

                list.add(obj);
            }
            return "redirect:/orca/admin/schedule.do?page=1";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/home.do";
        }

    }
  
    // 차량 리스트
    @GetMapping(value = "/admin/carlist.do")
    public String carlistGET(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page) {
        try {
            if (page == 0) {
                return "redirect:/orca/admin/carlist.do?page=1";
            }

            PageRequest pageRequest = PageRequest.of((page - 1), 5);
            long total = carService.total();

            List<Landtransportation> list = carService.selectlistCar(pageRequest);
            model.addAttribute("list", list);
            model.addAttribute("pages", (total - 1) / 5+1);
            model.addAttribute("user", user);
            return "/SG/admin/car_arrangement";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/home.do";
        }
    }

}
