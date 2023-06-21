package com.finalproject.JE.service;

import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaClientRepository;
import com.finalproject.JE.mapper.ClientMapper;
import com.finalproject.entity.Client;
import com.finalproject.entity.ClientProjection;
import com.finalproject.entity.ClientProjection2;

import ch.qos.logback.core.encoder.EchoEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JEserviceImpl implements JEservice{
    final ClientMapper cMapper;
    final DJOrcaClientRepository cRepository; 

    @Override
    public int insertCompanyOne(Client obj) {
        try{
            cRepository.save(obj);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int checkId(String id) {
    try{
      int result= cMapper.checkId(id);
      return result;
    }catch(Exception e){
        e.printStackTrace();
        return -1;
    }
    }

    @Override
    public Client deactiveClient(String id) {
        try{
        Client client=cRepository.findById(id).orElse(null);
        return client;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    
    @Override
    public Client updatePassword(String id) {
        try{
            Client client=cRepository.findById(id).orElse(null);
            return client;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ClientProjection findClientId(Client obj){
        try{
        ClientProjection client=cRepository.findByIdchkaAndBrn(obj.getIdchka(), obj.getBrn());
        return client;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ClientProjection2 findClientPassword(Client obj) {
        try{
            ClientProjection2 client=cRepository.findByIdAndBrn(obj.getId(), obj.getBrn());
            return client;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client Mypage(String id) {
        try{
            Client client=cRepository.findById(id).orElse(null);
            return client;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client updateInfo(String id) {
       try{
        Client client=cRepository.findById(id).orElse(null);
        return client;
       }catch(Exception e){
        e.printStackTrace();
        return null;
       }
    }

    @Override
    public Client image(String id) {
        try{
            Client obj=cRepository.findById(id).orElse(null);
            return obj;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
