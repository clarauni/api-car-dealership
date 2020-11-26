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

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    //get all list of cars order by sale date descendant
    @GetMapping("/orderBySaleDate")
    public ResponseEntity<Stream<Car>> getAllOrderBySaleDate (Pageable pageable) {
        return ResponseEntity.ok(carRepository.findAll(pageable).stream().sorted(Comparator.comparing(Car::getSaleDate).reversed()));
    }

    //get all list of cars order by deposit date descendant
    @GetMapping("/orderByDespositDate")
    public ResponseEntity<Stream<Car>> getAllOrderByDepositDate (Pageable pageable) {
        return ResponseEntity.ok(carRepository.findAll(pageable).stream().sorted(Comparator.comparing(Car::getDepositDate).reversed()));
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