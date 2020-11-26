package com.cardealership.carrestservice.repository;

import com.cardealership.carrestservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByBrand(String brand);
}
