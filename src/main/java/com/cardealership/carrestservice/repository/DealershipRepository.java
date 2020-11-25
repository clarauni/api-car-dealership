package com.cardealership.carrestservice.repository;

import com.cardealership.carrestservice.model.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealershipRepository extends JpaRepository<Dealership, Long> {
}
