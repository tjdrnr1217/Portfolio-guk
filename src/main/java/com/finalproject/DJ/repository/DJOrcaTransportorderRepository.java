package com.finalproject.DJ.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.Transportorder;

@Repository
public interface DJOrcaTransportorderRepository extends JpaRepository<Transportorder, String> {
    
    List<Transportorder> findByApplication_id(String id);

    //추가
    long countByApplication_applicationno(BigInteger applicationno);
    
}
