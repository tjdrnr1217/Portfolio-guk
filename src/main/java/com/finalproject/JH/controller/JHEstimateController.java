package com.finalproject.JH.controller;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

import com.finalproject.JH.service.JHApplicationService;
import com.finalproject.JH.service.JHCargoService;
import com.finalproject.JH.service.JHClientService;
import com.finalproject.JH.service.JHEstimateService;
import com.finalproject.JH.service.JHScheduleService;
import com.finalproject.entity.Application;
import com.finalproject.entity.Cargo;
import com.finalproject.entity.Client;
import com.finalproject.entity.Estimate;
import com.finalproject.entity.Schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class JHEstimateController {

    final JHCargoService cargoService;
    final JHScheduleService scheduleService;
    final JHEstimateService estimateService;
    final JHClientService clientService;
    final JHApplicationService applicationService;

    @PostMapping(value = "/company/estimatetotalpage.do")
    public String estimatetotalPOST(@ModelAttribute Estimate estimate, @ModelAttribute Cargo cargo, @ModelAttribute Application application){
        try {
            // cargo 먼저 넣기
            Cargo cargo1 = cargoService.insertCargo(cargo);

            if(cargo1 != null) {
                // estimate 채우기
                estimate.setCargo(cargo1);
                log.info("estimate =>{}",estimate.toString());
                estimateService.insertEstimate(estimate);

                // application 채우기
                Optional<Client> list = clientService.selectClientId(estimate.getId());
                application.setCargo(cargo1);
                application.setName(list.get().getName());
                application.setPhone(list.get().getPhone());
                application.setEmail(list.get().getEmail());
                log.info("application =>{}",application.toString());
                applicationService.insertApplication(application);
            }
            return "redirect:/orca/company/mypage.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/company/estimatepage1.do?cargoweight=0&cargotype=&locationtype=&period=0&scheduleno=&page=1";
        }
    }

    @GetMapping(value = "/company/estimatetotalpage.do")
    public String estimatetotalGET(Principal principal, Model model, @AuthenticationPrincipal User user,
                    @RequestParam(name = "location", defaultValue = "", required = false) String location,
                    @RequestParam(name = "period", defaultValue = "0", required = false) long period,
                    @RequestParam(name = "cargoweight", defaultValue = "0", required = false) long cargoweight,
                    @RequestParam(name = "cargotype", defaultValue = "", required = false) String cargotype,
                    @RequestParam(name = "locationtype", defaultValue = "", required = false) String locationtype,
                    @RequestParam(name = "scheduleno", defaultValue = "0", required = false) BigInteger scheduleno
                    ){
        try {
            long cargofare = 0;
            long containerfare = 0;
            if(cargotype.equals("A")){model.addAttribute("cargotype", "일반화물"); cargofare = 85;}
            else if(cargotype.equals("B")){model.addAttribute("cargotype", "기계하역"); cargofare = 51;}
            else if(cargotype.equals("C")){model.addAttribute("cargotype", "무연탄"); cargofare = 27;}
            else if(cargotype.equals("D")){model.addAttribute("cargotype", "송유관하역"); cargofare = 75;}
            else if(cargotype.equals("E")){model.addAttribute("cargotype", "20ft"); cargofare = 1161;}

            if(locationtype.equals("deliver")){
                containerfare = 247;
                if(location.equals("1")){model.addAttribute("locationtype","OrcaHub서울대지점");}
                else if(location.equals("2")){model.addAttribute("locationtype","OrcaHub평창지점");}
                else if(location.equals("3")){model.addAttribute("locationtype","OrcaHub청주지점");}
                else if(location.equals("4")){model.addAttribute("locationtype","OrcaHub대구지점");}
                else if(location.equals("5")){model.addAttribute("locationtype","OrcaHub전남지점");}
            }
            else if(locationtype.equals("port")){
                containerfare = 59;
                model.addAttribute("locationtype", "야적장");
            }
            
            List<Schedule> list = scheduleService.selectSchedulenoSchedule(scheduleno);
            
            // 항구기본료 + [하루항구사용료*보관기간]항구체화료 
            BigInteger portfacfare = list.get(0).getPort().getTotalfee().add(BigInteger.valueOf(containerfare* period));
            // [화물료*화물량]화물입항료 + [화물료*화물량*보관기간]화물체화료
            BigInteger cargofacfare = BigInteger.valueOf(cargofare * cargoweight * ( 1 + period ));
            BigInteger totalfare =  cargofacfare.add(portfacfare);
            
            String id = principal.getName();
            String keepingperiod = String.valueOf(period);

            model.addAttribute("list", list);
            model.addAttribute("id", id);
            model.addAttribute("cargoweight", cargoweight);
            model.addAttribute("keepingperiod", keepingperiod);
            model.addAttribute("totalfare", totalfare);
            model.addAttribute("portfacfare", portfacfare);
            model.addAttribute("cargofacfare", cargofacfare);
            model.addAttribute("scheduleno", scheduleno);
            model.addAttribute("user", user);

            return "/JH/Orca_estimatetotalpage";
        } 
        catch (Exception e) {
            e.printStackTrace();
            return "/JH/Orca_estimatepage1";
        }       
    }

    @GetMapping(value = "/company/estimatepage2.do")
    public String estimate2GET(Model model, @AuthenticationPrincipal User user,
                    @RequestParam(name = "cargoweight", defaultValue = "0", required = false) long cargoweight,
                    @RequestParam(name = "cargotype", defaultValue = "", required = false) String cargotype,
                    @RequestParam(name = "locationtype", defaultValue = "", required = false) String locationtype,
                    @RequestParam(name = "period", defaultValue = "0", required = false) long period,
                    @RequestParam(name = "scheduleno", defaultValue = "0", required = false) long scheduleno){

        try {
            if((cargoweight == 0 || cargotype.equals("") || locationtype.equals("") || scheduleno == 0 || period == 0)) {
                return "redirect:/orca/company/estimatepage1.do?cargoweight=" + cargoweight + "&cargotype=" + cargotype + "&locationtype=" + locationtype + "&period=" + period + "&scheduleno=" + scheduleno + "&page=1";
            }
            if(locationtype.equals("port")){
                return "redirect:/orca/company/estimatetotalpage.do?cargoweight=" + cargoweight + "&cargotype=" + cargotype + "&locationtype=" + locationtype + "&period=" + period + "&scheduleno=" + scheduleno;
            }
            List<Schedule> list = scheduleService.selectSchedulenoSchedule(BigInteger.valueOf(scheduleno));
            String[] adate= list.get(0).getArrivaldate().split("-");
            long adate1 = Long.parseLong( adate[2] ) + 2;
            String arrivaldate = adate[0] + "-" + adate[1] + "-" + adate1;
    
            model.addAttribute("cargoweight", cargoweight);
            model.addAttribute("cargotype", cargotype);
            model.addAttribute("locationtype", locationtype);
            model.addAttribute("period", period);
            model.addAttribute("scheduleno", scheduleno);
            model.addAttribute("adate", arrivaldate);
            model.addAttribute("user", user);
          
            return "/JH/Orca_estimatepage2";
        } catch (Exception e) {
            e.printStackTrace();
            return "/JH/Orca_estimatepage1";
        }
    }

    // 127.0.0.1:9090/ROOT/orca/estimatepage1.do?weight=&cargoType=&transport=&chk=
    @GetMapping(value = "/company/estimatepage1.do")
    public String estimate1GET(Model model, @AuthenticationPrincipal User user,
                    @RequestParam(name = "scheduleno", defaultValue = "0", required = false) long scheduleno){
        try {
            if( scheduleno == 0) {
                return "redirect:/orca/company/estimatepage.do";
            }
            model.addAttribute("scheduleno", scheduleno);
            model.addAttribute("user", user);
            return "/JH/Orca_estimatepage1";
        } 
        catch (Exception e) {
            e.printStackTrace();
            return "/JH/Orca_estimatepage1";
        } 
    }
}
