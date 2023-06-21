package com.finalproject.DJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.finalproject.DJ.dto.Location;
import com.finalproject.DJ.dto.Ship;
import com.finalproject.DJ.mapper.OrcaLocationScheduleMapper;
import com.finalproject.DJ.repository.DJOrcaOrderTableRepository;
import com.finalproject.DJ.repository.OrcaShipRepository;
import com.finalproject.entity.Ordertable;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequestScope
public class OrcaLocationScheduleServiceImpl implements OrcaLocationScheduleService {
    final String format = "locationservice => {}";
    @Autowired OrcaLocationScheduleMapper lMapper;
    @Autowired DJOrcaOrderTableRepository orderTableRepository;
    @Autowired OrcaShipRepository shipRepository;

    @Override
    public void shipLocationUpdate(Ship ship) {
        try {
            int ret = lMapper.shipLocationUpdate(ship);
            if(ret == 1) {
                log.info(format, ship.getPoint());
            }
        } catch (Exception e) {
            
        }
    }

    @Override
    public Location shipLocationSelect(String point) {
        try {
            log.info(format, point);
            return lMapper.shipLocationSelect(point);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public Ship shipSelectOne(String srn) {
        try {
            return lMapper.shipSelectOne(srn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Ordertable orderTableSelectOne(String id) {
        try {
            return orderTableRepository.findById(id).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
