package com.finalproject.DJ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.dto.Location;
import com.finalproject.DJ.dto.Ship;
import com.finalproject.DJ.mapper.OrcaLocationScheduleMapper;
import com.finalproject.DJ.repository.DJOrcaNotransviewRepository;
import com.finalproject.entity.Notransview;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrcaSchedulerServiceImpl implements OrcaSchedulerService {

    @Autowired OrcaLocationScheduleMapper lMapper;
    @Autowired DJOrcaNotransviewRepository notransviewRepository;

    @Override
    public List<Ship> shipSelectList() {
        try {
            return lMapper.shipSelectList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Location LocationSelect(String point) {
        try {
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
    public int shipStateUpdate(String srn, String state) {
        try {
            return lMapper.shipStateUpdate(srn, state);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int shipPointUpdate(Ship ship) {
        try {
            return lMapper.shipLocationUpdate(ship);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Notransview> DepartureDateCheck() {
        try {
            return notransviewRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
