package com.finalproject.DJ.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.DJ.dto.Location;
import com.finalproject.DJ.dto.Ship;
import com.finalproject.entity.Notransview;

@Service
public interface OrcaSchedulerService {
    
    public List<Ship> shipSelectList();
    
    public Ship shipSelectOne(String srn);

    public int shipStateUpdate(String srn, String state);

    public Location LocationSelect(String point);

    public int shipPointUpdate(Ship ship);

    public List<Notransview> DepartureDateCheck();
}
