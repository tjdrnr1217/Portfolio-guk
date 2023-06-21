package com.finalproject.SG.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finalproject.entity.Ordertable;

@Service
public interface OrdertableService {
    //
    public long count(BigInteger no);
    //
    Optional<Ordertable> selectone(String no);

    List<Ordertable> ordertableselect();

    Ordertable ordertablesave(Ordertable ordertable);
}
