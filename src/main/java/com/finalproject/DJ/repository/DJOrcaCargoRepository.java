package com.finalproject.DJ.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.Cargo;

@Repository
public interface DJOrcaCargoRepository extends JpaRepository<Cargo, BigInteger> {

    List<Cargo> findAllByClient_idOrderByClient_idDesc(String id, Pageable pageable);
    
    long countByClient_id(String id);

    public List<Cargo> findByClient_id(String id,Pageable pageable);

    // SG 추가
    List<Cargo> findByClient_idAndCargotypeContaining(String id, String cargotype, Pageable pageable);
    long countByClient_idAndCargotypeContaining(String id, String cargotype);

    List<Cargo> findByClient_idAndLocationtypeContaining(String id, String arrivalport, Pageable pageable);
    long countByClient_idAndLocationtypeContaining(String id, String locationtype);
}
