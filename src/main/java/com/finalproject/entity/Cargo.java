package com.finalproject.entity;

import java.math.BigInteger;

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

import lombok.Data;


@Entity
@Table(name = "CARGO")
@Data
@SequenceGenerator(name = "SEQ_CARGO_NO", sequenceName = "SEQ_CARGO_NO", initialValue = 1, allocationSize = 1)
public class Cargo {

    @Id
    @Column(name = "CARGONUMBER")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARGO_NO")
    private BigInteger cargonumber;

    private String cargotype;

    private BigInteger cargoweight;

    private String locationtype;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    private Client client;

    // @ToString.Exclude
    // @OneToOne(mappedBy = "cargo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Application application;    
}
