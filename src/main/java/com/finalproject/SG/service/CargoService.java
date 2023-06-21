package com.finalproject.SG.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.entity.Cargo;

@Service
public interface CargoService {
    public List<Cargo> selectlistCargo(String id,Pageable pageable);
    
    public long total(String id);

    public Optional<Cargo> selectOne(BigInteger no );

    public List<Cargo> selectlisttype(String id, String type, Pageable pageable);
    public long totaltype(String id, String type);

    public List<Cargo> selectlistlocation(String id, String location, Pageable pageable);
    public long totallocation(String id, String location);

    
}
