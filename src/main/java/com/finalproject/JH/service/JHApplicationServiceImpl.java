package com.finalproject.JH.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaApplicationRepository;
import com.finalproject.entity.Application;
import com.finalproject.entity.Cargo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JHApplicationServiceImpl implements JHApplicationService{

    final DJOrcaApplicationRepository applicationRepository;

    @Override
    public Application insertApplication(Application application) {
        try {
            return applicationRepository.save(application);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Application> findcargonumber(BigInteger cargonumber) {
        try{
        List<Application> alist = applicationRepository.findByCargo_Cargonumber(cargonumber);
        return alist;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }   
    
}
