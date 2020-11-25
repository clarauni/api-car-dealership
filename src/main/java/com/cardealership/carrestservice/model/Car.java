package com.cardealership.carrestservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String brand;
    @NotNull
    private float cost;
    @NotNull  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date saleDate;
    @NotNull  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date depositDate;
    @NotNull
    private boolean sold;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Float.compare(car.cost, cost) == 0 &&
                sold == car.sold &&
                Float.compare(car.salePrice, salePrice) == 0 &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(saleDate, car.saleDate) &&
                Objects.equals(depositDate, car.depositDate) &&
                Objects.equals(registration, car.registration) &&
                Objects.equals(dealership, car.dealership);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, cost, saleDate, depositDate, sold, registration, salePrice, dealership);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", cost=" + cost +
                ", saleDate=" + saleDate +
                ", depositDate=" + depositDate +
                ", sold=" + sold +
                ", registration='" + registration + '\'' +
                ", salePrice=" + salePrice +
                ", dealership=" + dealership +
                '}';
    }
}
