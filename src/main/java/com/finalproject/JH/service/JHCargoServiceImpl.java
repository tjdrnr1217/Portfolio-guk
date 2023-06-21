package com.finalproject.JH.service;

import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaCargoRepository;
import com.finalproject.entity.Cargo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JHCargoServiceImpl implements JHCargoService{

    final DJOrcaCargoRepository cargoRepository;

    @Override
    public Cargo insertCargo(Cargo cargo) {
        try {
            return cargoRepository.save(cargo);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
}
