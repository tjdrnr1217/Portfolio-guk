package com.finalproject.JH.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.entity.Estimate;

@Service
public interface JHEstimateService {
    
    public Estimate insertEstimate(Estimate estimate);

    public List<Estimate> adminEstimateList();

    public List<Estimate> adminEstimateListPage(Pageable pageable);

    public long totalEstimate();
 
}
