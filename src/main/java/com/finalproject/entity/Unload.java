package com.finalproject.entity;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "UNLOAD")
@SequenceGenerator(name = "SEQ_UNLOAD_NO", sequenceName = "SEQ_UNLOAD_NO", initialValue = 1, allocationSize = 1)
public class Unload {
    
    @Id
    @Column(name = "UNLOADNO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UNLOAD_NO")
    private BigInteger unloadno;

    private String portname;

    private String landingdevicename;

    private BigInteger landingability;

    private String unloadplace;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "PORTCODE", referencedColumnName = "PORTCODE")
    private Port port;
}
