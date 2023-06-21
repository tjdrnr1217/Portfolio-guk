package com.finalproject.JH.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.finalproject.SG.dto.Schedule;


@Mapper
public interface JHScheduleMapper {

    public long totalFilterDepartureAndArrival(Schedule scbedule);
    
}
