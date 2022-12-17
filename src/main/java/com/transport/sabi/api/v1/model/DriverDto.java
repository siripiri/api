package com.transport.sabi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transport.sabi.api.domain.Address;

import javax.persistence.Embedded;

public class DriverDto {
    public Long id;
    public Long lorryId;
    public String numberPlate;

    @Embedded
    public Address address;
    public String dob;
    public String childrenDetails;
    public String driverName;
    @JsonProperty("api_url")
    public String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLorryId() {
        return lorryId;
    }

    public void setLorryId(Long lorryId) {
        this.lorryId = lorryId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getChildrenDetails() {
        return childrenDetails;
    }

    public void setChildrenDetails(String childrenDetails) {
        this.childrenDetails = childrenDetails;
    }
}
