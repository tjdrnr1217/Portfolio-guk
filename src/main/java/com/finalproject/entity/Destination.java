package com.finalproject.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DESTINATION")
@Data
public class Destination {

    @Id
    @Column(name = "DESTINATIONNAME")
    private String destinationname;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private BigInteger maxcargo;

    private String containertype;

    private String operationtime;

    // @ToString.Exclude
    // @OneToOne(mappedBy = "destination", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Application application;

}
