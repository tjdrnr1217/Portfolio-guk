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
public class Location {
    private String point;
    private double longitude;
    private double latitude;
    private String departureport;
    private String arrivalport;
}
