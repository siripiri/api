package com.transport.sabi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transport.sabi.api.domain.Address;

import javax.persistence.Embedded;

public class LocationsDTO {
    public Long id;
    public String distributorName;
    @Embedded
    public Address address;
    public Long kmAllocated;
    @JsonProperty("api_url")
    public String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
