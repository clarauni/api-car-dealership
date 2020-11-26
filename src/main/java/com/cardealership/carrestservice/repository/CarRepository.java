package com.cardealership.carrestservice.repository;

import com.cardealership.carrestservice.model.Car;
import com.cardealership.carrestservice.model.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByBrand(String brand);
}