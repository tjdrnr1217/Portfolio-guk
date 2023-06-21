package com.finalproject.SG.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.entity.Ship;

@Service
public interface ShipService {

    public List<Ship> selectlistShip(Pageable pageable);

    public long total();

    public Optional<Ship> selectoneShip(String srn);
 
}
