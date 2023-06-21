package com.finalproject.entity;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "LANDTRANSPORTATION")
@Data
public class Landtransportation {

   @Id
   @Column(name = "NUMBER")
   private String number;

   private String unloadplace;

   private String rider;

   private String vehicletype;

   private BigInteger weight;

   // @ToString.Exclude
   // @OneToOne(mappedBy = "landtransportation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // private Transportorder transportorder;

}
