package com.finalproject.DJ.dto;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class Waitingview {
    private BigInteger cargonumber;
    private String cargotype;
    private BigInteger cargoweight;
    private String id;
    private String locationtype;
    private BigInteger applicationno;
    private String destinationname;
    private BigInteger estimateno;
    private BigInteger scheduleno;
    private String departureport;
    private String arrivalport;
    private Date arrivaldate;
    private Date departuredate;
    private String portcode;
}
