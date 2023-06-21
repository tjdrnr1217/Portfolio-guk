package com.finalproject.SG.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.entity.Transportorder;

@Service
public interface TransportorderService {
    public long countapplicationno(BigInteger applicationno);

    public List<Transportorder> selectall();

    public int transportordersave(Transportorder obj);
}
