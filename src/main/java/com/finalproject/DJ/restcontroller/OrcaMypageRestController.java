package com.finalproject.DJ.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.finalproject.DJ.service.OrcaMypageService;
import com.finalproject.entity.FinalorderView;
import com.finalproject.entity.Notransview;
import com.finalproject.entity.Ordertable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/orca")
@RequiredArgsConstructor
@Slf4j
public class OrcaMypageRestController {
    final String format = "MypageRC => {}";
    @Autowired OrcaMypageService mypageService; 
    
    final BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

    @PostMapping(value = "/reviewcompleted.json")
    public Map<String, Object> reviewcompletedPOST(
        @RequestBody Map<String, String> id) {
        Map<String, Object> retMap = new HashMap<>();
        log.info(format, id.get("id"));
        log.info(format,id.get("page"));
        PageRequest pageRequest = PageRequest.of((Integer.parseInt(id.get("page"))-1), 5);
        try {
            List<Ordertable> list = mypageService.OrderTableList(id.get("id"),pageRequest);
            // List<Estimate> list = mypageService.EstimateTableList(id.get("id"));
            long total = mypageService.CountOrderTable(id.get("id"));
            log.info(format, total);
            retMap.put("status", 200);
            retMap.put("list", list);
            retMap.put("pages", (total-1)/5+1);
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;

    }
    @PostMapping(value = "/delete.json")
    public Map<String, Object> deletePOST(@RequestBody Map<String,String> key) {
        Map<String, Object> retMap = new HashMap<>();
        try {
            log.info("json=>{}", key.get("chktarget"));
            if(key.get("chktarget") != null ) {
                Ordertable ordertable = new Ordertable();
                ordertable.setOrderno(key.get("chktarget"));
                int rst = mypageService.CacelOrderTable(ordertable.getOrderno());
                retMap.put("status", 200);
                retMap.put("rst", rst);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }

    @PostMapping(value = "/finalorderview.json")
    public Map<String,Object> listPOST(@RequestBody Map<String,String> key) {
        log.info(format, key.get("orderno"));
        Map<String, Object> retMap = new HashMap<>();
        try {
            if(key.get("orderno") != null ) {
                Notransview notransview = mypageService.NoTransViewOne(key.get("orderno"));
                FinalorderView finalorderView = mypageService.FinalOrderViewOne(key.get("orderno"));
                log.info(format, notransview.toString());
                if(finalorderView == null ) {
                    retMap.put("flist", notransview);
                    retMap.put("status", 200);
                }
                else {
                    retMap.put("flist", finalorderView);
                    retMap.put("status", 200);
                }
                
                
            }   
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        
        return retMap;
    }
}
