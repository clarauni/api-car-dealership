package com.cardealership.carrestservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dealership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String address;

    @OneToMany(mappedBy = "dealership", cascade = CascadeType.ALL)
    private Set<Car> cars = new HashSet<>();

    public Dealership(String address) {
        this.address = address;
    }

    public Dealership(String address, Set<Car> cars) {
        this.address = address;
        this.cars = cars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        for (Car c: cars) {
            c.setDealership(this);
        }
    }
}
