package com.finalproject.entity;

import java.math.BigDecimal;
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

@Data
@Entity
@Table(name = "LOCATION")
public class Location {
    
    @Id
    @Column(name = "POINT")
    private String point;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String departureport;

    private String arrivalport;

    // @ToString.Exclude
    // @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE)
    // List<Ship> ship = new ArrayList<>();
    
}
