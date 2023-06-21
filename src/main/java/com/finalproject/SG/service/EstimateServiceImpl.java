package com.finalproject.SG.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaEstimateRepository;
import com.finalproject.entity.Estimate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstimateServiceImpl implements EstimateService {

    final DJOrcaEstimateRepository eRepository;

    @Override
    public Optional<Estimate> selectlistone(BigInteger estimateno) {
        try {
            return eRepository.findById(estimateno);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
