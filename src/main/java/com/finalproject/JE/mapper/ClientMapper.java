package com.finalproject.JE.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.finalproject.entity.Client;

@Mapper
public interface ClientMapper {

    //아이디 중복체크
    public int checkId(@Param("id") String id);
    
    // //회원탈퇴(update)
    // public int deactivate(Client client);
    
}
