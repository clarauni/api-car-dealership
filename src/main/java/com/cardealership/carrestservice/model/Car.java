package com.cardealership.carrestservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String brand;
    @NotNull
    private float cost;
    @NotNull
    private Date saleDate;
    @NotNull
    private Date depositDate;
    @NotNull
    private boolean sold;
    @NotNull
    private String registration;
    @NotNull
    private float salePrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dealership_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Dealership dealership;

    public Car() {
    }

    public Car(String brand, float cost, Date saleDate, Date depositDate, boolean sold, String registration, float salePrice, Dealership dealership) {
        this.id = id;
        this.brand = brand;
        this.cost = cost;
        this.saleDate = saleDate;
        this.depositDate = depositDate;
        this.sold = sold;
        this.registration = registration;
        this.salePrice = salePrice;
        this.dealership = dealership;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public Dealership getDealership() {
        return dealership;
    }

    public void setDealership(Dealership dealership) {
        this.dealership = dealership;
    }
}
