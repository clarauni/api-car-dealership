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

    public Float getBenefits () {
        Float benefits = 0.0f;
        List<Car> cars = carRepository.findAll();
        for (Car c: cars) benefits += c.getSalePrice() - c.getCost();
        return benefits;
    }
    //get all benefits
    @GetMapping ("/benefits")
    public ResponseEntity<Float> showBenefits() {
        return ResponseEntity.ok(this.getBenefits());
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

    //get car by id
    @GetMapping("/id/{id}")
    public ResponseEntity<Car> getById(@PathVariable int id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(!optionalCar.isPresent()) return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(optionalCar.get());
    }

    //get car by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<Car> getByBrand (@PathVariable String brand) {
        Optional<Car> optionalCar = carRepository.findByBrand(brand);
        if (!optionalCar.isPresent()) return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(optionalCar.get());
    }

    //add car in an existing dealership
    @PutMapping("/create/{address}/")
    public ResponseEntity<Car> create (@PathVariable String address, @Valid @RequestBody Car car) {
       Optional<Dealership> optionalDealership = dealershipRepository.findByAddress(address);
        if (!optionalDealership.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        car.setDealership(optionalDealership.get());
        carRepository.save(car);
        return ResponseEntity.noContent().build();

    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<Car> update(@Valid @RequestBody Car car, @PathVariable int id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (!optionalCar.isPresent()) return ResponseEntity.unprocessableEntity().build();

        //car is not sold
        if (!optionalCar.get().isSold()) {
            //update registration
            if (optionalCar.get().getRegistration().isBlank()) {
                optionalCar.get().setRegistration(car.getRegistration());
            }
            //update sale date and final price
            if (optionalCar.get().isAvailable() && optionalCar.get().getSaleDate() == null &&
                car.getSalePrice() > 0 && car.getSaleDate() != null) {
                optionalCar.get().setSalePrice(car.getSalePrice());
                optionalCar.get().setSaleDate(car.getSaleDate());
                optionalCar.get().setSold(true);
            }

            carRepository.save(optionalCar.get());
        }
        return ResponseEntity.noContent().build();
    }

    //retire a car
    @PutMapping("/retire/{id}")
    public ResponseEntity<Car> retire(@PathVariable int id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (!optionalCar.isPresent()) return ResponseEntity.unprocessableEntity().build();
        //car is not sold
        if (!optionalCar.get().isSold()) {
            //car not available
            optionalCar.get().setAvailable(false);
            carRepository.save(optionalCar.get());
        }
        return ResponseEntity.noContent().build();
    }

    //delete a car
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Car> delete (@PathVariable int id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (!optionalCar.isPresent()) return ResponseEntity.unprocessableEntity().build();
        carRepository.delete(optionalCar.get());
        return ResponseEntity.noContent().build();
    }


}
