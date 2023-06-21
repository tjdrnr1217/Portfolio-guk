package com.finalproject.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Immutable
@Data
@Entity
@Table(name = "NOTRANSVIEW")
public class Notransview {

    @Id
    @Column(name = "ORDERNO")
    private String orderno;

    @Column(name = "ID")
    private String id;

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

    @Column(name = "STATE")
    private String state;

    @Column(name = "PORTFACFARE")
    private BigInteger portfacfare;

    @Column(name = "SCHEDULENO")
    private BigInteger scheduleno;

    @Column(name = "CARGONUMBER")
    private BigInteger cargonumber;

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

    @Column(name = "CARGOTYPE")
    private String cargotype;

    @Column(name = "CARGOWEIGHT")
    private BigInteger cargoweight;

    @Column(name = "TOTALFARE")
    private BigInteger totalfare;
}
