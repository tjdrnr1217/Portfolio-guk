package com.finalproject.DJ.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.DJ.dto.Location;
import com.finalproject.DJ.dto.Ship;
import com.finalproject.DJ.service.OrcaLocationScheduleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/orca")
@RequiredArgsConstructor
@Slf4j
public class OrcaLocationScheduleRestController {
    final String format = "OrcaLocationScheduleRestController => {}";
    final String[] bj = {"부산항","부산항P1","부산항P2","부산항P3","부산항P4","부산항P5","제주항"};
    
    @Autowired OrcaLocationScheduleService lService;

    @PostMapping(value = "/location.json")
    public Map<String,Object> locationPOST(@RequestBody Ship ship) {
        log.info(format, ship);
        log.info(format, ship.getPoint());
        for(int i=0;i<bj.length;i++) {
            if(ship.getPoint().equals(bj[i])) {
                Location location = lService.shipLocationSelect(ship.getPoint());
                log.info(format, location);
                
                Map<String, Object> retMap = new HashMap<>();
                retMap.put("title",location.getPoint());
                retMap.put("latitude",location.getLatitude());
                retMap.put("longitude",location.getLongitude());
                retMap.put("next", bj[i+1]);
                return retMap;
            }
        }
        return null;
    }
}
