package com.finalproject.DJ.service;

import org.springframework.stereotype.Service;

import com.finalproject.DJ.dto.Location;
import com.finalproject.DJ.dto.Ship;
import com.finalproject.entity.Ordertable;


@Service
public interface OrcaLocationScheduleService {

    public void shipLocationUpdate(Ship ship);

    public Location shipLocationSelect(String point);

    public Ship shipSelectOne(String srn);

    public Ordertable orderTableSelectOne(String id);

}
