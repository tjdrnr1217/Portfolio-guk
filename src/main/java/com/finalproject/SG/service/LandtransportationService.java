package com.finalproject.SG.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.entity.Landtransportation;

@Service
public interface LandtransportationService {
    public List<Landtransportation> selectall();
}
