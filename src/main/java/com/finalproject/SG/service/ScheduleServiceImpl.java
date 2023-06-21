package com.finalproject.SG.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaScheduleRepository;
import com.finalproject.entity.Schedule;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    
    final DJOrcaScheduleRepository sRepository;

    @Override
    public int insertSchedule(Schedule obj) {
        try {
            sRepository.save(obj);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Schedule> selectlistSchedule(Pageable pageable) {
        try {
            return sRepository.findAllByOrderBySchedulenoDesc(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long total() {
        try {
            return sRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Schedule> updateselectlistSchedule(List<BigInteger> no) {
        try {
            return sRepository.findBySchedulenoIn(no,Sort.by(Sort.Direction.ASC, "scheduleno"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Schedule updatelistSchedule(BigInteger no) {
        try {
            return sRepository.findById(no).orElse(null);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Schedule> selectone(BigInteger no) {
        try {
            return sRepository.findById(no);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
   
}
