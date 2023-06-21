package com.finalproject.DJ.controller;

import java.math.BigInteger;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.DJ.dto.Ship;
import com.finalproject.DJ.service.OrcaLocationScheduleService;
import com.finalproject.DJ.service.OrcaMypageService;
import com.finalproject.DJ.service.OrcaPayMentService;
import com.finalproject.entity.Client;
import com.finalproject.entity.FinalorderView;
import com.finalproject.entity.Notransview;
import com.finalproject.entity.Ordertable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class OrcaPaymentController {
    
    @Autowired OrcaPayMentService paymentService;
    @Autowired OrcaLocationScheduleService locationScheduleService;
    @Autowired OrcaMypageService mypageService;

    @GetMapping(value = "/company/payment.do")
    public String paymemtGET(
        Model model,
        Principal principal,
        @RequestParam(name = "orderno") String orderno) {
        try {
            int sum = 0;
            DecimalFormat decimalFormat = new DecimalFormat("###,###");
            log.info("{}", orderno);
            Notransview notransview = paymentService.allpaylist(orderno);
            Client clientinfo = paymentService.clientinfo(notransview.getId());
            Ship ship = locationScheduleService.shipSelectOne(notransview.getSrn());
            // 선박무게
            float shipweight = (float)ship.getShipweight();
            float dv10 = shipweight/10;
            // 입출항료
            float shipentryfee = (dv10 * 135) + (dv10 * 24);
            // 접안료
            float Dockage = dv10 * 120;
            // 선박료
            float Anchorage = dv10 * 61;
            //계선료
            float mooringfee = dv10 * 9;

            int shipfare = (int)shipentryfee + (int)Dockage + (int)Anchorage + (int)mooringfee;
            log.info("{}", shipfare);
            log.info("{}", clientinfo.toString());

            BigInteger ordertablefare = notransview.getTotalfare();
            
            FinalorderView finalorderView = paymentService.TransPortCharge(orderno);

            if(finalorderView == null) {
                sum = ordertablefare.intValue()+shipfare;
            }
            else {
                BigInteger transportorderfare = finalorderView.getTransportcharge();
                sum = ordertablefare.intValue()+shipfare+transportorderfare.intValue();
            }
            log.info("{}", sum);

            model.addAttribute("sum", decimalFormat.format(sum));
            model.addAttribute("shipfare", decimalFormat.format(shipfare));
            model.addAttribute("client", clientinfo);
            model.addAttribute("user", mypageService.clientSelectOne(principal.getName()));

            return "/DJ/Orca_Payment";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/company/mypage.do";
        }
    }

    @GetMapping(value = "/admin/finalpaymentlist.do")
    public String finalpayGET(Model model,
        @RequestParam(name = "page",defaultValue = "0",required = false ) int page,
        @AuthenticationPrincipal User user) {
        try {
            if(page == 0){
                return "redirect:/orca/admin/finalpaymentlist.do?page=1";
            }
            PageRequest pageRequest = PageRequest.of((page-1), 10);
            long total = paymentService.CountOrderTable();
            List<Ordertable> list = paymentService.OrderTableList(pageRequest);

            model.addAttribute("list", list);
            model.addAttribute("pages", (total-1)/10+1);
            model.addAttribute("user", user);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/admin/home.do";
        }
        return "/DJ/Orca_AdminFinalPaymentsList";
    }
}
