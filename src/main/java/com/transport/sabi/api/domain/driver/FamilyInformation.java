package com.transport.sabi.api.domain.driver;

import com.transport.sabi.api.domain.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class FamilyInformation extends BaseEntity {
    private String name;
    private String dob;
    private String relationShip;
    @ManyToOne
    private Driver driver;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
