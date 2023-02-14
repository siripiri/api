package com.transport.sabi.api.v1.model.trips;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TripsTableDto {
    public Long id;
    public String date;
    public Long kmCovered;
    public String timeTaken;
    public Long locationId;
    public String distributorName;
    public String city;
    public Long kmAllocated;
    public Long lorryId;
    public String lorryNumberPlate;
    public Long driverId;
    public String driverName;
    @JsonProperty("api_url")
    public String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getKmCovered() {
        return kmCovered;
    }

    public void setKmCovered(Long kmCovered) {
        this.kmCovered = kmCovered;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getKmAllocated() {
        return kmAllocated;
    }

    public void setKmAllocated(Long kmAllocated) {
        this.kmAllocated = kmAllocated;
    }

    public Long getLorryId() {
        return lorryId;
    }

    public void setLorryId(Long lorryId) {
        this.lorryId = lorryId;
    }

    public String getLorryNumberPlate() {
        return lorryNumberPlate;
    }

    public void setLorryNumberPlate(String lorryNumberPlate) {
        this.lorryNumberPlate = lorryNumberPlate;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
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
}
