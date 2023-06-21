package com.finalproject.DJ.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.entity.Landtransportation;

public interface OrcaLandtransportationRepository extends JpaRepository<Landtransportation, String>{

    // SG추가
    List<Landtransportation> findAllBy(Pageable pageable);
    
    // SG추가
    public long count();
}
