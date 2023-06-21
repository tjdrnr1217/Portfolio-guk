package com.finalproject.JE.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.JE.mapper.ClientMapper;
import com.finalproject.JE.service.JEservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/client")
@RequiredArgsConstructor
@Slf4j
public class OrcaIdCheckRestcontroller {
    
    // final ClientMapper cMapper;
    final String format="OrcaJoinCompanyController => {}";
    final JEservice jeservice;

    @GetMapping(value = "/idcheck.json")
    public Map<String, Object> idcheckGET(@RequestParam(name="id") String id) {
        Map<String, Object> retMap = new HashMap<>();
        log.info("abcd=>{}");
        
        // int ret=cMapper.checkId(id);
        int ret=jeservice.checkId(id);
        log.info("가나다가나다 => {}",id);
        try{
            if(ret==1){
                retMap.put("ret", 1);
            }
            else{
                retMap.put("ret", 0);
            }
        }catch(Exception e){
        e.printStackTrace();
        retMap.put("ret", 0);
        return retMap;
        }
        return retMap;
    }
}
