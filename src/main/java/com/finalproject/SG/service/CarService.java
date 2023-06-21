package com.finalproject.SG.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.entity.Landtransportation;

@Service
public interface CarService {

    public List<Landtransportation> selectlistCar(Pageable pageable);

    public long total();
    
}
