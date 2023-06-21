package com.finalproject.SG.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.finalproject.SG.dto.Schedule;

@Mapper
public interface ScheduleMapper {

    public int insertSchedule(Schedule obj);
    
}
