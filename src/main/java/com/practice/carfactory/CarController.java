package com.practice.carfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/cars/{vin}")
    public Car getSingleCar(@PathVariable long vin){
        return service.getCar(vin);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return service.getAllCars();
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> saveCar(@RequestBody @Valid Car car) throws URISyntaxException {
        Long vin = service.saveCar(car);
        return ResponseEntity.created(new URI("/cars/" + vin)).body(car);
    }
}
