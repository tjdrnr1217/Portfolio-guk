package com.finalproject.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "ORDERVIEW")
public class OrderView {
    
    @Id
    @Column(name = "ORDERNO")
    private String orderno;

    @Column(name = "ID")
    private String id;

    @Column(name = "ESTIMATENO")
    private BigInteger estimateno;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ORDERDATE")
    private Date orderdate;

    @Column(name = "APPLICATIONNO")
    private BigInteger applicationno;
}
