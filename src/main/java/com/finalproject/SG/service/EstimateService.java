package com.finalproject.SG.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finalproject.entity.Estimate;

@Service
public interface EstimateService {

    public Optional<Estimate> selectlistone(BigInteger estimateno);
    
}
