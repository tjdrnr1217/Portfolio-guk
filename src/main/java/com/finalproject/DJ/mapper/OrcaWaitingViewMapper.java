package com.finalproject.DJ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.finalproject.DJ.dto.Waitingview;

@Mapper
public interface OrcaWaitingViewMapper {
    
    public List<Waitingview> selectWaitingViewList(@Param("id") String id, @Param("start") int page, @Param("end") int end);
    
    public long countWaitingView(@Param("id") String id);
}
