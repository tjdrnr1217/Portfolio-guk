package com.finalproject.JH.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.entity.Schedule;

@Service
public interface JHScheduleService {

    public List<Schedule> selectSchedulePage(Pageable pageable);

    public List<Schedule> selectSchedulenoSchedule(BigInteger no);

    public long totalSchedule();

    public List<Schedule> selectFilterDepartureSchedulePage(String departureport, Pageable pageable);
    public List<Schedule> selectFilterArrivalSchedulePage(String arrivalport, Pageable pageable);
    public List<Schedule> selectFilterTwoPage(String departureport, String arrivalport, Pageable pageable);

    public long totalFilterDapartureSchedule(String departureport);
    public long totalFilterArrivalSchedule(String arrivalport);
    
}
