package com.finalproject.entity;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PAYMENT")
@SequenceGenerator(name = "SEQ_PAYMENT_NO", sequenceName = "SEQ_PAYMENT_NO", initialValue = 1, allocationSize = 1)
public class Payment {
    
    @Id
    @Column(name = "PNO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAYMENT_NO")
    private BigInteger pno;

    private String payway;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ORDERNO", referencedColumnName = "ORDERNO")
    private Ordertable ordertable;

    
}
