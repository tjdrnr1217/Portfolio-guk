package com.finalproject.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "CLIENT")
public class Client {
    
    @Id
    @Column(name = "ID")
    private String id;

    private String brn;

    private String phone;

    private String password;

    private BigInteger postcode;

    private String address;

    private String daddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    private Date regdate;

    private BigInteger chk;

    private String name;

    private String role;

    private String idchkq;

    private String idchka;

    private String email;

    @Lob
    @ToString.Exclude
    private byte[] filedata;

    private BigInteger filesize;

    private String filename;

    private String filetype;

    // @ToString.Exclude
    // @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    // List<Cargo> cargo = new ArrayList<>();
}
