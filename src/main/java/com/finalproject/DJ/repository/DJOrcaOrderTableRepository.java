package com.finalproject.DJ.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.Ordertable;

@Repository
public interface DJOrcaOrderTableRepository extends JpaRepository<Ordertable, String> {
    
    long countByStateEqualsAndId(String state,String id);

    List<Ordertable> findAllByIdOrderByIdDesc(String id, Pageable pageable);
    
    long countById(String id);

    // DJ 추가
    List<Ordertable> findAllByOrderByIdDesc(Pageable pageable);

    //정은지현추가

    long countByEstimate_Estimateno(BigInteger estimateno);

}
