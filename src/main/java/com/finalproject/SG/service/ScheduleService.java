package com.finalproject.SG.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.entity.Schedule;

@Service
public interface ScheduleService {

     public int insertSchedule(Schedule obj);

     public List<Schedule> selectlistSchedule(Pageable pageable);

     public long total();

     public List<Schedule> updateselectlistSchedule(List<BigInteger> no);
     
     public Schedule updatelistSchedule(BigInteger no);

     public Optional<Schedule> selectone(BigInteger no);
     
    
     
}
