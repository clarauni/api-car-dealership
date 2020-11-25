package com.cardealership.carrestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "brand")
    private String brand;

    @Column (name = "cost")
    private float cost;

    @Column (name = "saleDate")
    private Date saleDate;

    @Column (name = "depositDate")
    private Date depositDate;

    @Column (name = "sold")
    private boolean sold;

    @Column (name = "registration")
    private String registration;

    @Column (name = "salePrice")
    private float salePrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dealership_id" , nullable = false)
    @JsonIgnore
    private Dealership dealership;

    public Car() {
    }

    public Car(String brand, float cost, Date saleDate, Date depositDate, boolean sold, String registration, float salePrice, Dealership dealership) {
        this.brand = brand;
        this.cost = cost;
        this.saleDate = saleDate;
        this.depositDate = depositDate;
        this.sold = sold;
        this.registration = registration;
        this.salePrice = salePrice;
        this.dealership = dealership;
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
