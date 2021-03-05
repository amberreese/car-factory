package com.practice.carfactory;

import com.practice.carfactory.repositories.CarRepository;

import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Car> getAllCars(){
        return repository.findAll();
    }

    @Override
    public Long saveCar(Car car){
        return repository.save(car).getVin();
    }

    @Override
    public Car getCar(Long vin){
        Optional<Car> car = repository.findById(vin);
        return car.map(c -> new Car(c.getMake(), c.getModel(), c.getYear(), c.getVin())).orElse(null);
    }

}
