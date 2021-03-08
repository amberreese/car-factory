package com.practice.carfactory;

import com.practice.carfactory.repositories.CarRepository;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarTest {
    CarRepository repository = mock(CarRepository.class);

    @Test
    void getAllCars(){
        when(repository.findAll()).thenReturn(Collections.singletonList(new Car(1l,"Volvo", "XC90", 2015)));

        CarService service = new CarServiceImpl(repository);
        List<Car> carList = service.getAllCars();

        assertEquals(1, carList.size());
    }

    @Test
    void getCar(){
        when(repository.findById(2l)).thenReturn(Optional.of(new Car(2l, "BMW", "i8", 2019)));

        CarService service = new CarServiceImpl(repository);
        Car car = service.getCar(2l);
        assertEquals(2l, car.getVin());
    }

    @Test
    void saveCar(){
        Car car = new Car(3l, "Buick", "Regal", 2016);
        when(repository.save(car)).thenReturn(car);

        CarService service = new CarServiceImpl(repository);
        Long vin = service.saveCar(new Car(3l,"Buick", "Regal", 2016));
        assertEquals(3l, vin);
    }

    @Test
    void deleteCar(){
        Car car2 = new Car(3l, "Buick", "Regal", 2016);
        when(repository.findById(3l)).thenReturn(Optional.of(car2));

        CarService service = new CarServiceImpl(repository);

        service.deleteCar(3l);
        verify(repository, times(1)).deleteById(3l);

    }
}
