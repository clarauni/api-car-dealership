package com.cardealership.carrestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "dealerships")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Dealership implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "dealership", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Car> cars;

    public Dealership() {
    }

    public Dealership(String address) {
        this.address = address;
    }

    public Dealership(String address, Set<Car> cars) {
        this.address = address;
        this.cars = cars;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
