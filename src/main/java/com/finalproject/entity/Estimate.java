package com.finalproject.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "ESTIMATE")
@SequenceGenerator(name = "SEQ_ESTIMATE_NO", sequenceName = "SEQ_ESTIMATE_NO", initialValue = 1, allocationSize = 1)
public class Estimate {

    @Id
    @Column(name = "ESTIMATENO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ESTIMATE_NO")
    private BigInteger estimateno;

    private String id;

    private BigInteger totalfare;

    private BigInteger portfacfare;

    private String state;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(updatable = false)
    private Date estimatedate;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "SCHEDULENO", referencedColumnName = "SCHEDULENO")
    private Schedule schedule;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "CARGONUMBER", referencedColumnName = "CARGONUMBER")
    private Cargo cargo;

    @ToString.Exclude
    @OneToOne(mappedBy = "estimate",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Ordertable ordertable;


    // @ToString.Exclude
    // @OneToOne(mappedBy = "estimate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Ordertable ordertable;

}
