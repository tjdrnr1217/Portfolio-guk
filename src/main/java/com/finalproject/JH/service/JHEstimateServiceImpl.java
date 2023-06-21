package com.finalproject.JH.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaEstimateRepository;
import com.finalproject.entity.Estimate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JHEstimateServiceImpl implements JHEstimateService{

    final DJOrcaEstimateRepository estimateRepository;

    @Override
    public Estimate insertEstimate(Estimate estimate) {
        try {
            return estimateRepository.save(estimate);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Estimate> adminEstimateList() {
        try {
            return estimateRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Estimate> adminEstimateListPage(Pageable pageable) {
        try {
            return estimateRepository.findAllByOrderByEstimatenoDesc(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long totalEstimate() {
        try {
            return estimateRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
