package com.finalproject.SG.dto;

import java.math.BigInteger;


import lombok.Data;

@Data
public class Schedule {
    private BigInteger scheduleno;
    private String departureport;
    private String arrivalport;
    private String portcode;
    private String arrivaldate;
    private String departuredate;    
    
}
