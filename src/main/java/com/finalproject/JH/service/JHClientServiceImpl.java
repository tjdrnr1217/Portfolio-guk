package com.finalproject.JH.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaClientRepository;
import com.finalproject.entity.Client;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JHClientServiceImpl implements JHClientService{
    
    final DJOrcaClientRepository clientRepository;

    @Override
    public Optional<Client> selectClientId(String id) {
        try {
            return clientRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
