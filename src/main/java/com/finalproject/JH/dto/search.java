package com.finalproject.JH.dto;

import lombok.Data;

@Data
public class search {
    final String[] departure = {"전체", "부산항", "인천항", "속초항", "목포항", "완도항", "여수항", "광양항", "포항항", "진해항", "통영항", "울산항", "제주항", "서귀포항"};
    final String[] all = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
    final String[] arrival = {"전체", "부산항", "인천항", "속초항", "목포항", "완도항", "여수항", "광양항", "포항항", "진해항", "통영항", "울산항", "제주항", "서귀포항"};

    private String departureport = "";
    private String arrivalport = "";
    private int page = 1;
}
 
    
