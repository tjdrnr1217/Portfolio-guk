package com.finalproject.JH.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaScheduleRepository;
import com.finalproject.JH.mapper.JHScheduleMapper;
import com.finalproject.entity.Schedule;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JHScheduleServiceImpl implements JHScheduleService{

    final DJOrcaScheduleRepository scheduleRepository;
    final JHScheduleMapper scheduleMapper;

    @Override
    public List<Schedule> selectSchedulePage(Pageable pageable) {
        try {
            return scheduleRepository.findAllByOrderBySchedulenoDesc(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Schedule> selectSchedulenoSchedule(BigInteger no) {
        try {
            return scheduleRepository.findByScheduleno(no);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long totalSchedule() {
        try {
            return scheduleRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Schedule> selectFilterDepartureSchedulePage(String departureport, Pageable pageable) {
        try {
            return scheduleRepository.findByDepartureportContaining(departureport, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Schedule> selectFilterArrivalSchedulePage(String arrivalport, Pageable pageable) {
        try {
            return scheduleRepository.findByArrivalportContaining(arrivalport, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long totalFilterDapartureSchedule(String departureport) {
        try {
            return scheduleRepository.countByDepartureportContaining(departureport);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long totalFilterArrivalSchedule(String arrivalport) {
        try {
            return scheduleRepository.countByArrivalportContaining(arrivalport);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Schedule> selectFilterTwoPage(String departureport, String arrivalport, Pageable pageable) {
        try {
            return scheduleRepository.findByDepartureportAndArrivalport(departureport, arrivalport, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
