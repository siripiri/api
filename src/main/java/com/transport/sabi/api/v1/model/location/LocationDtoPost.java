package com.transport.sabi.api.v1.model.location;

import com.transport.sabi.api.domain.Address;

import jakarta.persistence.Embedded;

public class LocationDtoPost {
    public String distributorName;
    @Embedded
    public Address address;
    public Long kmAllocated;

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getKmAllocated() {
        return kmAllocated;
    }

    public void setKmAllocated(Long kmAllocated) {
        this.kmAllocated = kmAllocated;
    }
}
