package com.finalproject.SG.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.OrcaLandtransportationRepository;
import com.finalproject.entity.Landtransportation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    final OrcaLandtransportationRepository landtransportationRepository;

    @Override
    public List<Landtransportation> selectlistCar(Pageable pageable) {
       try {
        return  landtransportationRepository.findAllBy(pageable);
       } catch (Exception e) {
        e.printStackTrace();
        return null;
       }
    }

    @Override
    public long total() {
        try {
            return landtransportationRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
