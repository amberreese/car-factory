package com.practice.carfactory;

import com.practice.carfactory.repositories.CarRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarTest {
    @Test
    void getAllCars(){
        CarRepository repository = mock(CarRepository.class);
        when(repository.findAll()).thenReturn(Collections.singletonList(new Car("Volvo", "XC90", 2015, 1L)));

        CarService service = new CarServiceImpl(repository);
        List<Car> carList = service.getAllCars();

        assertEquals(1, carList.size());
    }

    @Test
    void getCar(){
        CarRepository repository = mock(CarRepository.class);
        when(repository.findById(2l)).thenReturn(Optional.of(new Car("BMW", "i8", 2019, 2l)));

        CarService service = new CarServiceImpl(repository);
        Car car = service.getCar(2l);
        assertEquals(2l, car.getVin());

    }
}
