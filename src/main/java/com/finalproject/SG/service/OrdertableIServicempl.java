package com.finalproject.SG.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaOrderTableRepository;
import com.finalproject.entity.Ordertable;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdertableIServicempl implements OrdertableService {

    final DJOrcaOrderTableRepository orderTableRepository;

    @Override
    public long count(BigInteger no) {
       try {
        return orderTableRepository.countByEstimate_Estimateno(no);
       } catch (Exception e) {
        e.printStackTrace();
        return -1;
       }
    }

    @Override
    public Optional<Ordertable> selectone(String no) {
       try {
        return orderTableRepository.findById(no);
       } catch (Exception e) {
        e.printStackTrace();
        return null;
       }
    }

    @Override
    public List<Ordertable> ordertableselect() {
        try{
        List<Ordertable> list = orderTableRepository.findAll();
        return list;
        }catch(Exception e){
        e.printStackTrace();
        return null;
        }
    }

    @Override
    public Ordertable ordertablesave(Ordertable ordertable) {
        try{
            Ordertable ot = orderTableRepository.save(ordertable);
            return ot;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
