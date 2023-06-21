package com.finalproject.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "PORT")
public class Port {
    
    @Id
    @Column(name = "PORTCODE")
    private String portcode;

    private String portname;

    private BigInteger totalfee;

    // @ToString.Exclude
    // @OneToMany(mappedBy = "port", cascade = CascadeType.REMOVE)
    // List<Unload> unload = new ArrayList<>();
}
