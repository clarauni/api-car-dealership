package com.cardealership.carrestservice.controller;

import com.cardealership.carrestservice.model.Car;
import com.cardealership.carrestservice.model.Dealership;
import com.cardealership.carrestservice.repository.CarRepository;
import com.cardealership.carrestservice.repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping ("/api/cars")
public class CarController {

    private final CarRepository carRepository;
    private final DealershipRepository dealershipRepository;

    @Autowired
    public CarController(CarRepository carRepository, DealershipRepository dealershipRepository) {
        this.carRepository = carRepository;
        this.dealershipRepository = dealershipRepository;
    }

    //get all list of cars
    @GetMapping
    public  ResponseEntity<Page<Car>> getAll (Pageable pageable) {
        return ResponseEntity.ok(carRepository.findAll(pageable));
    }

    //get car by brand
    @GetMapping("/{brand}")
    public ResponseEntity<Car> getByBrand (@PathVariable String brand) {
        Optional<Car> optionalCar = carRepository.findByBrand(brand);
        if (!optionalCar.isPresent()) return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(optionalCar.get());
    }

    //add car in an existing dealership
    @PutMapping("/{address}/")
    public ResponseEntity<Car> create (@PathVariable String address, @Valid @RequestBody Car car) {
       Optional<Dealership> optionalDealership = dealershipRepository.findByAddress(address);
        if (!optionalDealership.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        car.setDealership(optionalDealership.get());
        carRepository.save(car);
        return ResponseEntity.noContent().build();

    }

}
