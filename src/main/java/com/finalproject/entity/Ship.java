package com.finalproject.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
@Table(name = "SHIP")
public class Ship {
    
    @Id
    @Column(name = "SRN")
    private String srn;

    private String sname;

    private String type;

    private String state;

    private BigInteger maxcargo;

    private BigInteger shipweight;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "Point", referencedColumnName = "Point")
    private Location location;

    // @ToString.Exclude
    // @OneToMany(mappedBy = "ship", cascade = CascadeType.REMOVE)
    // List<Ordertable> ordertable = new ArrayList<>();
}
