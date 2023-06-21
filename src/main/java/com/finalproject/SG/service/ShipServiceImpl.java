package com.finalproject.SG.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.OrcaShipRepository;
import com.finalproject.entity.Ship;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {

    final OrcaShipRepository orcaShipRepository;

    @Override
    public List<Ship> selectlistShip(Pageable pageable) {
        try {
            return orcaShipRepository.findAllBy(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long total() {
       try {
        return orcaShipRepository.count();
       } catch (Exception e) {
        e.printStackTrace();
        return -1;
       }
    }

    @Override
    public Optional<Ship> selectoneShip(String srn) {
      try {
        return orcaShipRepository.findById(srn);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
    
}
