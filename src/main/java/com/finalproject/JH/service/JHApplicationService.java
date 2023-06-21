package com.finalproject.JH.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.entity.Application;
import com.finalproject.entity.Cargo;

@Service
public interface JHApplicationService {

    public Application insertApplication(Application application);
    
    public List<Application> findcargonumber(BigInteger cargonumber);
}
