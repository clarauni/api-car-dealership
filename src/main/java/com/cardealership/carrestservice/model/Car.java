package com.cardealership.carrestservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date saleDate;

    @NotNull  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date entryDate;

    //value sold false by default
    private boolean sold = false;

    //value available false by default
    private boolean available = true;

    private String registration;

    @NotNull
    private float salePrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dealership_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Dealership dealership;

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

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
