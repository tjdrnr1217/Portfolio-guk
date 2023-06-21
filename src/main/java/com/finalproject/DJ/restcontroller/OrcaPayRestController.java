package com.finalproject.DJ.restcontroller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.DJ.dto.Ship;
import com.finalproject.DJ.service.OrcaLocationScheduleService;
import com.finalproject.DJ.service.OrcaPayMentService;
import com.finalproject.entity.Client;
import com.finalproject.entity.FinalorderView;
import com.finalproject.entity.Notransview;
import com.finalproject.entity.Ordertable;
import com.finalproject.entity.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/orca")
@RequiredArgsConstructor
@Slf4j
public class OrcaPayRestController {
    
    final String format = "PayRestController => {}";
    @Autowired OrcaPayMentService paymentService;
    @Autowired OrcaLocationScheduleService locationScheduleService;

    @PostMapping(value = "/paylist.json")
    public Map<String,Object> paylistPOST( @RequestBody Map<String,String> paychk) {
        Map<String, Object> retMap = new HashMap<>();
        try {
            if(paychk.get("paybtn").equals("ok")) {

                Notransview notransview = paymentService.allpaylist(paychk.get("orderno"));
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

                log.info(format, ship.toString());
                log.info(format, clientinfo.toString());
                log.info(format, notransview.toString());
                int sum;   
                FinalorderView finalorderView = paymentService.TransPortCharge(paychk.get("orderno"));
                BigInteger ordertablefare = notransview.getTotalfare();
                if(finalorderView == null) {
                    sum = ordertablefare.intValue()+shipfare;
                }
                else {
                    BigInteger transportorderfare = finalorderView.getTransportcharge();
                    sum = ordertablefare.intValue()+transportorderfare.intValue()+shipfare;
                }
                retMap.put("name", clientinfo.getName());
                retMap.put("email", clientinfo.getEmail());
                retMap.put("phone", clientinfo.getPhone());
                retMap.put("address", clientinfo.getAddress());
                retMap.put("postcode", clientinfo.getPostcode());
                retMap.put("status", 200);
                retMap.put("sum", sum);
                retMap.put("rst", finalorderView);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }

    @PostMapping(value = "/paysuccess.json")
    public Map<String, Object> paysuccessPOST(@RequestBody Map<String, String> chk) {
        Map<String, Object> retMap = new HashMap<>();
        try {
            if(chk.get("success").equals("ok")) {
                int ret = paymentService.OrderTableUpdateState(chk.get("orderno"));
                Ordertable ordertable = paymentService.OrderTableOne(chk.get("orderno"));
                Payment payment = new Payment();
                String payway = chk.get("paymethod");

                payment.setOrdertable(ordertable);
                payment.setPayway(payway);

                int payRet = paymentService.insertPaymentTable(payment);

                log.info(format, payway);
                log.info(format, payRet);
                log.info(format, ret);
                
                retMap.put("status", 200);
                retMap.put("ret",ret);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }


}
