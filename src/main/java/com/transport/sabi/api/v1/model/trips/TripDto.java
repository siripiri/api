package com.transport.sabi.api.v1.model.trips;

import com.transport.sabi.api.v1.model.location.LocationTripDto;
import com.transport.sabi.api.v1.model.lorry.LorryIdPlateDto;

public class TripDto {
    public Long id;
    public String date;
    public Long plantToDistributor;
    public Long distributorToPlant;
    public String plantStart;
    public String desEnd;
    public String desStart;
    public String plantEnd;
    public LocationTripDto location;
    public LorryIdPlateDto lorry;

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

    public Long getPlantToDistributor() {
        return plantToDistributor;
    }

    public void setPlantToDistributor(Long plantToDistributor) {
        this.plantToDistributor = plantToDistributor;
    }

    public Long getDistributorToPlant() {
        return distributorToPlant;
    }

    public void setDistributorToPlant(Long distributorToPlant) {
        this.distributorToPlant = distributorToPlant;
    }

    public String getPlantStart() {
        return plantStart;
    }

    public void setPlantStart(String plantStart) {
        this.plantStart = plantStart;
    }

    public String getDesEnd() {
        return desEnd;
    }

    public void setDesEnd(String desEnd) {
        this.desEnd = desEnd;
    }

    public String getDesStart() {
        return desStart;
    }

    public void setDesStart(String desStart) {
        this.desStart = desStart;
    }

    public String getPlantEnd() {
        return plantEnd;
    }

    public void setPlantEnd(String plantEnd) {
        this.plantEnd = plantEnd;
    }

    public LocationTripDto getLocation() {
        return location;
    }

    public void setLocation(LocationTripDto location) {
        this.location = location;
    }

    public LorryIdPlateDto getLorry() {
        return lorry;
    }

    public void setLorry(LorryIdPlateDto lorry) {
        this.lorry = lorry;
    }
}
