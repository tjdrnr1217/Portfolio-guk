package com.finalproject.SG.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaCargoRepository;
import com.finalproject.entity.Cargo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    final DJOrcaCargoRepository cRepository;

    // 로그인 회원에 대한 화물내역
    @Override
    public List<Cargo> selectlistCargo(String id,Pageable pageable) {
        try {
            return cRepository.findByClient_id(id,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Cargo> selectOne(BigInteger no) {
       try {
        return cRepository.findById(no);
       } catch (Exception e) {
        e.printStackTrace();
        return null;
       }
    }

    @Override
    public List<Cargo> selectlisttype(String id,String type, Pageable pageable) {
        try {
            return cRepository.findByClient_idAndCargotypeContaining(id,type, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long totaltype(String id, String type) {
        try {
            return cRepository.countByClient_idAndCargotypeContaining(id,type);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public List<Cargo> selectlistlocation(String id,String location, Pageable pageable) {
        try {
            return cRepository.findByClient_idAndLocationtypeContaining(id,location, pageable);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public long totallocation(String id, String location) {
        try {
            return cRepository.countByClient_idAndLocationtypeContaining(id, location);
        } catch (Exception e) {
            return -1;
        }
    }

    // 로그인 회원에 대한 화물내역 개수
    @Override
    public long total(String id) {
        try {
            return cRepository.countByClient_id(id);
        } catch (Exception e) {
            return -1;
        }
    }
    
}
