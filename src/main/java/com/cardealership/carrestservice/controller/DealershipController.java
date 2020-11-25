package com.cardealership.carrestservice.controller;


import com.cardealership.carrestservice.model.Dealership;
import com.cardealership.carrestservice.repository.CarRepository;
import com.cardealership.carrestservice.repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/dealerships")
public class DealershipController {

    private   DealershipRepository dealershipRepository;
    private   CarRepository carRepository;

    @Autowired
    public DealershipController(DealershipRepository dealershipRepository, CarRepository carRepository) {
        this.dealershipRepository = dealershipRepository;
        this.carRepository = carRepository;
    }

    //get all dealership

    @GetMapping
    public ResponseEntity<Page<Dealership>> getAll (Pageable pageable) {
        return ResponseEntity.ok(dealershipRepository.findAll(pageable));
    }

    //get one dealership by address

    @GetMapping("/{address}/")
    public ResponseEntity<Dealership> getByAddress (@PathVariable String address) {
        Optional<Dealership> optionalDealership = dealershipRepository.findByAddress(address);
        if (!optionalDealership.isPresent())return  ResponseEntity.unprocessableEntity().build();
        return  ResponseEntity.ok(optionalDealership.get());
    }

    //add dealership

    @PostMapping
    public ResponseEntity<Dealership> create (@Valid @RequestBody Dealership dealership) {
        Dealership savedDealership = dealershipRepository.save(dealership);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDealership.getId()).toUri();
        return  ResponseEntity.created(location).body(savedDealership);
    }




}
