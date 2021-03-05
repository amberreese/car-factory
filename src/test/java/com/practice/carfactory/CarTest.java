package com.practice.carfactory;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    @Test
    void getAllCars(){
        CarRepository carRepo = new CarRepository();
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Volvo", "XC90", 2015, 1L));
        assertEquals(carList.size(), carRepo.getAll().size());
    }

    @Test
    void getCar(){
        Car car = new Car("Volvo", "XC90", 2012, 1l);
        assertEquals("Volvo", car.getMake());
        assertEquals("XC90", car.getModel());
        assertEquals(2012, car.getYear());
    }
}
