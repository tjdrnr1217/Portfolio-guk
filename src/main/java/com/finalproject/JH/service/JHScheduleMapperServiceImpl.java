package com.finalproject.JH.service;

import org.springframework.stereotype.Service;

import com.finalproject.JH.mapper.JHScheduleMapper;
import com.finalproject.SG.dto.Schedule;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JHScheduleMapperServiceImpl implements JHScheduleMapperService{

    final JHScheduleMapper scheduleMapper;
    
    @Override
    public long totalFilterTwoSchedule(Schedule schedule) {
        try {
            return scheduleMapper.totalFilterDepartureAndArrival(schedule);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
   
}
