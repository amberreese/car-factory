package com.practice.carfactory;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();
    Long saveCar(Car car);
    Car getCar(Long vin);
    void deleteCar(Long vin);
}
