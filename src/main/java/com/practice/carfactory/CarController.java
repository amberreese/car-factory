package com.practice.carfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
class CarController {

    private final CarService service;

    private static String getEmployees(){
        final String uri = "http://localhost:3000/jpa/users";

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate build = restTemplateBuilder.basicAuthentication("username", "password").build();

        String forObject = build.getForObject(uri, String.class);

        return forObject;

    }

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/cars/employees")
    public String getDealershipEmployees(){
       return getEmployees();
    }

    @GetMapping("/cars/{vin}")
    public Car getSingleCar(@PathVariable long vin){
        return service.getCar(vin);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(@RequestParam(value="make", required = false) String make){
        if (make == null){
            return service.getAllCars();
        }
        else{
            return service.getAllCarsByMake(make);

        }
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
