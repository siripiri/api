package com.transport.sabi.api.domain;

import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.domain.load.CylinderLine;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TripDetail extends BaseEntity {
    private String date;
    private Long plantToDistributor;
    private Long distributorToPlant;
    private String plantStart;
    private String desEnd;
    private String desStart;
    private String plantEnd;
    @ManyToOne
    public Location location;
    @ManyToOne
    public Lorry lorry;
    @ManyToOne
    public Driver driver;
    @OneToMany(mappedBy = "tripDetail", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<CylinderLine> cylinderLineSet = new HashSet<>();

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Lorry getLorry() {
        return lorry;
    }

    public void setLorry(Lorry lorry) {
        this.lorry = lorry;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Set<CylinderLine> getCylinderLineSet() {
        return cylinderLineSet;
    }

    public void setCylinderLineSet(Set<CylinderLine> cylinderLineSet) {
        this.cylinderLineSet = cylinderLineSet;
    }
}
