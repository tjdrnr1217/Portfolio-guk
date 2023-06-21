package com.finalproject.SG.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.OrcaLandtransportationRepository;
import com.finalproject.entity.Landtransportation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LantransportationServiceImpl implements LandtransportationService{
    final OrcaLandtransportationRepository landRepository;

    public List<Landtransportation> selectall() {
        try {
            List<Landtransportation> landlist = landRepository.findAll();
            return landlist;
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }
    
}
