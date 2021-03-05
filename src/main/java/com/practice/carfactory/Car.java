package com.practice.carfactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private String make;
    private String model;
    private Integer year;
    private Long vin;

}
