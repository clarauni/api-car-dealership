package com.cardealership.carrestservice.repository;

import com.cardealership.carrestservice.model.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DealershipRepository extends JpaRepository<Dealership, Integer> {

}
