package com.finalproject.DJ.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.Notransview;

@Repository
public interface DJOrcaNotransviewRepository extends JpaRepository<Notransview, String> {

    List<Notransview> findAllByIdOrderByIdDesc(String id, Pageable pageable);

    long countById(String id);

    long countByStateEqualsAndId(String state,String id);
}
