package com.finalproject.DJ.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.Ship;

@Repository
public interface OrcaShipRepository extends JpaRepository<Ship, String> {

    List<Ship> findBySnameIsNotNull(Pageable pageable);

    public long count();

    Ship findBySrn(String srn);

    // SG추가
    List<Ship> findAllBy(Pageable pageable);
}
