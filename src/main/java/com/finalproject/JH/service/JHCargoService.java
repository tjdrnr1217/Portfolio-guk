package com.finalproject.JH.service;

import org.springframework.stereotype.Service;

import com.finalproject.entity.Cargo;

@Service
public interface JHCargoService {
    
    public Cargo insertCargo(Cargo cargo);
}
