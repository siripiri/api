package com.transport.sabi.api.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Location extends BaseEntity {
    @Embedded
    private Address address;
    private String distributorName;
    private Long kmAllocated;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public Long getKmAllocated() {
        return kmAllocated;
    }

    public void setKmAllocated(Long kmAllocated) {
        this.kmAllocated = kmAllocated;
    }
}
