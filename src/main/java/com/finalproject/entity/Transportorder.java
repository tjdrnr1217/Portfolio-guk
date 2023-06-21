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
@Table(name = "TRANSPORTORDER")
@Data
public class Transportorder {
    
    @Id
    @Column(name = "INVOICENUMBER")
    private String invoicenumber;

    private String destination;

    private BigInteger transportcharge;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(updatable = false)
    private Date orderdate;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "APPLICATIONNO", referencedColumnName = "APPLICATIONNO")
    private Application application;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "NUMBER", referencedColumnName = "NUMBER")
    private Landtransportation landtransportation;

    // @ToString.Exclude
    // @OneToOne(mappedBy = "transportorder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Landpayment landpayment;
    
}
