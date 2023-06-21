package com.finalproject.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Immutable
@Data
@Entity
@Table(name = "FINALORDERVIEW")
public class FinalorderView {
    
    @Id
    @Column(name = "ORDERNO")
    private String orderno;

    @Column(name = "ID")
    private String id;

    @Column(name = "TOTALFARE")
    private BigInteger totalfare;

    @Column(name = "STATE")
    private String STATE;

    @Column(name = "ESTIMATENO")
    private BigInteger estimateno;

    @Column(name = "SRN")
    private String srn;

    @Column(name = "SNAME")
    private String sname;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "MAXCARGO")
    private BigInteger maxcargo;

    @Column(name = "POINT")
    private String point;

    @Column(name = "SHIPWEIGHT")
    private BigInteger shipweight;

    @Column(name = "PORTFACFARE")
    private BigInteger portfacfare;

    @Column(name = "SCHEDULENO")
    private BigInteger scheduleno;

    @Column(name = "DEPARTUREPORT")
    private String departureport;

    @Column(name = "ARRIVALPORT")
    private String arrivalport;

    @Column(name = "DEPARTUREDATE")
    private String departuredate;

    @Column(name = "ARRIVALDATE")
    private String arrivaldate;

    @Column(name = "APPLICATIONNO")
    private BigInteger applicationno;

    @Column(name = "CARGONUMBER")
    private BigInteger cargonumber;

    @Column(name = "CARGOTYPE")
    private String cargotype;

    @Column(name = "CARGOWEIGHT")
    private BigInteger cargoweight;

    @Column(name = "INVOICENUMBER")
    private String invoicenumber;

    @Column(name = "TRANSPORTCHARGE")
    private BigInteger transportcharge;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "NUMBER")
    private String number;
}
