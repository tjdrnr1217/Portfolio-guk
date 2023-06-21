package com.finalproject.DJ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.finalproject.DJ.dto.Location;
import com.finalproject.DJ.dto.Ship;

@Mapper
public interface OrcaLocationScheduleMapper {
    
    // 선박 조회
    public Ship shipSelectOne(String srn);

    // 선박 position update
    public int shipLocationUpdate(Ship ship);

    // 위도 경도 값 조회
    public Location shipLocationSelect(String point);

    // 선박 모두 조회
    public List<Ship> shipSelectList();

    // 선박 상태 업데이트
    public int shipStateUpdate(@Param("srn") String srn, @Param("state") String state);

}
