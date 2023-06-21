package com.finalproject.JH.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finalproject.entity.Client;

@Service
public interface JHClientService {

    public Optional<Client> selectClientId(String id);
    
}
