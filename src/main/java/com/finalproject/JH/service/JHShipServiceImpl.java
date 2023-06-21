package com.finalproject.JH.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.OrcaShipRepository;
import com.finalproject.entity.Ship;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JHShipServiceImpl implements JHShipService{
    
    final OrcaShipRepository shipRepository;
    
    @Override
    public List<Ship> selectShipPage(Pageable pageable) {
        try {
            return shipRepository.findBySnameIsNotNull(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long totalShip() {
        try {
            return shipRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Ship insertShip(Ship ship) {
        try {
            return shipRepository.save(ship);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Ship selectShip(String srn) {
        try {
            return shipRepository.findBySrn(srn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Ship deleteShip(Ship ship) {
        try {
            return shipRepository.save(ship);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
