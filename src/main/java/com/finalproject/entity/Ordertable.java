package com.finalproject.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "ORDERTABLE")
public class Ordertable {
    
    @Id
    @Column(name = "orderno")
    private String orderno;

    private String id;

    private String departureport;

    private String arrivalport;

    private BigInteger totalfare;

    private String state;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(updatable = false)
    private Date orderdate;


    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ESTIMATENO",referencedColumnName = "ESTIMATENO")
    private Estimate estimate;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "SRN", referencedColumnName = "SRN")
    private Ship ship;

    @ToString.Exclude
    @OneToOne(mappedBy = "ordertable", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Payment payment;

}
