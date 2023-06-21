package com.finalproject.DJ.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.OrderView;

@Repository
public interface DJOrcaOrderViewRepository extends JpaRepository<OrderView, String> {
    List<OrderView> findAllByIdOrderByIdDesc(String id, Pageable pageable);

    long countById(String id);

    long countByStateEqualsAndId(String state,String id);
}
