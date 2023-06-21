package com.finalproject.SG.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaTransportorderRepository;
import com.finalproject.entity.Transportorder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransportorderServiceImpl implements TransportorderService{

    final DJOrcaTransportorderRepository tpRepository;
    @Override
    public long countapplicationno(BigInteger applicationno) {
       try {
        return tpRepository.countByApplication_applicationno(applicationno);
       } catch (Exception e) {
        e.printStackTrace();
        return -1;
       }
    }
    @Override
    public List<Transportorder> selectall() {
        try{
        List<Transportorder> tlist = tpRepository.findAll();
        return tlist;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int transportordersave(Transportorder obj) {
        try {
            tpRepository.save(obj);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
