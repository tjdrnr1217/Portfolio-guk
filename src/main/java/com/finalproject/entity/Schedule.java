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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "SCHEDULE")
@SequenceGenerator(name = "SEQ_SCHEDULE_NO", sequenceName = "SEQ_SCHEDULE_NO", initialValue = 3782, allocationSize = 1)
public class Schedule {
    
    @Id
    @Column(name = "SCHEDULENO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SCHEDULE_NO")
    private BigInteger scheduleno;

    private String departureport;

    private String arrivalport;

    private String arrivaldate;

    private String departuredate;

    @ManyToOne()
    @JoinColumn(name = "PORTCODE", referencedColumnName = "PORTCODE")
    private Port port;

    // @ToString.Exclude
    // @OneToOne(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Estimate estimate;
    
}
