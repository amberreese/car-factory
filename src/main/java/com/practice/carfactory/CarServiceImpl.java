package com.practice.carfactory;

import com.practice.carfactory.repositories.CarRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    private static List<Car> cars = new ArrayList<>();

    static {
        cars.add(new Car(12l,"Chevy", "Tahoe",2019));
        cars.add(new Car(32l ,"Dodge", "Durango",2014));
        cars.add(new Car(11l,"Kia", "Sorento",2007));

    }

    @Override
    public List<Car> getAllCars(){
        return repository.findAll();
    }

    @Override
    public Long saveCar(Car car){

        Car saveCar = repository.save(car);
        return saveCar.getVin();
    }

    @Override
    public Car getCar(Long vin){
        Optional<Car> car = repository.findById(vin);
        return car.map(c -> new Car(c.getVin(), c.getMake(), c.getModel(), c.getYear())).orElse(null);
    }

    @Override
    public void deleteCar(Long vin){
        repository.deleteById(vin);
    }

}
