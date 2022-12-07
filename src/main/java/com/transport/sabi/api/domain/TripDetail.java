package com.transport.sabi.api.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TripDetail extends BaseEntity {
    private Timestamp date;
    private Long plantToDistributor;
    private Long distributorToPlant;
    private Timestamp plantStart;
    private Timestamp desEnd;
    private Timestamp desStart;
    private Timestamp plantEnd;
    @OneToOne
    public Location location;
    @OneToOne
    public Lorry lorry;
    @OneToMany(mappedBy = "tripDetail", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<CylinderLine> cylinderLineSet = new HashSet<>();

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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

    public Timestamp getPlantStart() {
        return plantStart;
    }

    public void setPlantStart(Timestamp plantStart) {
        this.plantStart = plantStart;
    }

    public Timestamp getDesEnd() {
        return desEnd;
    }

    public void setDesEnd(Timestamp desEnd) {
        this.desEnd = desEnd;
    }

    public Timestamp getDesStart() {
        return desStart;
    }

    public void setDesStart(Timestamp desStart) {
        this.desStart = desStart;
    }

    public Timestamp getPlantEnd() {
        return plantEnd;
    }

    public void setPlantEnd(Timestamp plantEnd) {
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

    public Set<CylinderLine> getCylinderLineSet() {
        return cylinderLineSet;
    }

    public void setCylinderLineSet(Set<CylinderLine> cylinderLineSet) {
        this.cylinderLineSet = cylinderLineSet;
    }
}
