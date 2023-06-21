package com.finalproject.entity;

import java.math.BigInteger;
import java.util.Date;

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

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Table(name = "APPLICATION")
@Entity
@SequenceGenerator(name = "SEQ_APPLICATION_NO", sequenceName = "SEQ_APPLICATION_NO", initialValue = 1, allocationSize = 1)
public class Application {
    @Id
    @Column(name = "APPLICATIONNO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPLICATION_NO")
    private BigInteger applicationno;

    private String id;

    private String name;

    private String phone;

    private String email;

    private String cname;

    private String keepingperiod;

    private String cargotype;

    private BigInteger cargoweight;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(updatable = false)
    private Date applydate;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "CARGONUMBER", referencedColumnName = "CARGONUMBER")
    private Cargo cargo;


    // @JsonIgnore json파일로 변경x ( 양방향 할때 json값으로 불러오지 않을 데이터에게 어노테이션)
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "DESTINATIONNAME", referencedColumnName = "DESTINATIONNAME")
    private Destination destination;

    // @ToString.Exclude
    // @OneToOne(mappedBy = "application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Transportorder transportorder;


    


}
