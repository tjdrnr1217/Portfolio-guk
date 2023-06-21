package com.finalproject.SG.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="regdate")
public class Client {
    private String id;
    private String brn;
    private String phone;
    private String password;
    private String address;
    private Date regdate;
    private long chk;
    private String name;
    private String role;
    private String idchkq;
    private String idchka;
    private String email;
    private byte[] filedata;
    private long filesize;
    private String filename;
    private String filetype;

}
