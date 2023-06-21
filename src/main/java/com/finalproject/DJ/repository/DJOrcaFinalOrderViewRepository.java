package com.finalproject.DJ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.FinalorderView;

@Repository
public interface DJOrcaFinalOrderViewRepository extends JpaRepository<FinalorderView, String> {
    
}
