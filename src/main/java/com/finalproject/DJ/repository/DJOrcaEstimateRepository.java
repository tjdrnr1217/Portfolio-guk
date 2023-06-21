package com.finalproject.DJ.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.Estimate;

@Repository
public interface DJOrcaEstimateRepository extends JpaRepository<Estimate, BigInteger>{

    List<Estimate> findAllByIdOrderByIdDesc(String id, Pageable pageable);

    long countById(String id);

    List<Estimate> findAllByOrderByEstimatenoDesc(Pageable pageable);

    long count();

    // DJ 추가
    long countByStateEqualsAndId(String state,String id);

    // DJ repository
}

