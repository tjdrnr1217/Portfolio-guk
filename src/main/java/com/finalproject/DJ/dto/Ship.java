package com.finalproject.DJ.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ship {
    private String srn;
    private String sname;
    private String type;
    private long maxcargo;
    private String point;
    private long shipweight;    
    private String state;
}
