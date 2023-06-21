package com.finalproject.DJ.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.Application;
import java.util.List;


@Repository
public interface DJOrcaApplicationRepository extends JpaRepository<Application, BigInteger>{
    
    List<Application> findById(String id);

    //정은지현추가
    List<Application> findByCargo_Cargonumber(BigInteger cargonumber);

}
