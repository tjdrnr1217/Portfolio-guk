package com.finalproject.DJ.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.Schedule;

@Repository
public interface DJOrcaScheduleRepository extends JpaRepository<Schedule, BigInteger> {
    // DJ 스케줄 repository

    List<Schedule> findAllByOrderBySchedulenoDesc(Pageable pageable);

    public long count();

    public List<Schedule> findAll();
    
    List<Schedule> findByScheduleno(BigInteger no);


    // SG추가
    List<Schedule> findBySchedulenoIn(List<BigInteger> scheduleno, Sort sort); // 파라미터를 실행할때 어떻게 sort할건지 

    // JH추가 Schedule조회필터기능
    List<Schedule> findByDepartureportContaining(String departureport, Pageable pageable);
    List<Schedule> findByArrivalportContaining(String arrivalport, Pageable pageable);
    List<Schedule> findByDepartureportAndArrivalport(String departureport ,String arrivalport, Pageable pageable);

    long countByDepartureportContaining(String departureport);
    long countByArrivalportContaining(String Arrivalport);

}
