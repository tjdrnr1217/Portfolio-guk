package com.finalproject.JH.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.entity.Ship;

@Service
public interface JHShipService {

    public List<Ship> selectShipPage(Pageable pageable);

    public long totalShip();

    public Ship insertShip(Ship ship);

    public Ship selectShip(String srn);

    public Ship deleteShip(Ship ship);
    
}
