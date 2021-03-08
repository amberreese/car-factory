package com.practice.carfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


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

    @DeleteMapping("/cars/{vin}")
    public ResponseEntity deleteCar(@PathVariable long vin){
        Optional<Car> car = Optional.ofNullable(service.getCar(vin));
        if(car.isPresent()){
            service.deleteCar(vin);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.notFound().build();
    }
}
