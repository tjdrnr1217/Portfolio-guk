package com.finalproject.DJ.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.Payment;

@Repository
public interface DJOrcaPaymentRepository extends JpaRepository<Payment, BigInteger> {
    
}
