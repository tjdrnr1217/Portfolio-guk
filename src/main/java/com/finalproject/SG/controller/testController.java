package com.finalproject.SG.controller;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.DJ.repository.OrcaShipRepository;
import com.finalproject.JH.service.JHEstimateService;
import com.finalproject.SG.service.ShipService;
import com.finalproject.entity.Estimate;
import com.finalproject.entity.Schedule;
import com.finalproject.entity.Ship;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class testController {

    final ShipService shipService;
    final JHEstimateService sEstimateService;

    // @PostMapping(value = "/admin/shipassignment.do")
    // public String shipassignmentPOST(Model model,
    //         @ModelAttribute Estimate estimate, @ModelAttribute Ship ship, @ModelAttribute Schedule schedule,
    //         @RequestParam(name = "srn") String srn,
    //         @RequestParam(name = "cargoweight")BigInteger cargoweight,
    //         @RequestParam(name = "estimateno") BigInteger estimateno) {
    //             try {
    //                 //최대적재량 무게 계산
    //                 Optional<Ship> obj = shipService.selectoneShip(srn);
    //                 BigInteger max = obj.get().getMaxcargo();
    //                 int total = max.intValue() - cargoweight.intValue();
    //                 BigInteger.valueOf(total);

    //                 log.info("{}",BigInteger.valueOf(total));
    //                 obj.get().setMaxcargo(BigInteger.valueOf(total));
                    
    //                 // 상태변화 완료
    //                 Optional<Estimate> obj2 = sEstimateService.selectlistone(estimateno);
    //                 log.info("{}", obj2.get().getState());
    //                 obj2.get().setState("완료");

                   

    //                 return "redirect:/orca/admin/ordertable.do";
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //                 return "redirect:/orca/home.do";
    //             }
    // }

}
