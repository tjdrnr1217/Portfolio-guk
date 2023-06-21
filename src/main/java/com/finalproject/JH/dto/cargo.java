package com.finalproject.JH.dto;

import java.math.BigInteger;

import lombok.Data;

@Data
public class cargo {
    private BigInteger cargonumber;
    private String cargotype;
    private BigInteger cargoweight;
    private String id;
    private String locationtype;
}
