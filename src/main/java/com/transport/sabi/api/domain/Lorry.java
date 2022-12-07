package com.transport.sabi.api.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lorry extends BaseEntity{
    private String numberPlate;
    private String type;
    private String modelNumber;
    private String manufacturer;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Lorry_Driver",
               joinColumns = @JoinColumn(name = "lorry_id"),
               inverseJoinColumns = @JoinColumn(name = "driver_id"))
    public Set<Driver> drivers = new HashSet<>();

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }
}
