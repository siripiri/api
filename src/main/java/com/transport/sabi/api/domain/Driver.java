package com.transport.sabi.api.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Driver extends BaseEntity {
    private String name;
    private String dob;
    private String childrenDetails;
    @Embedded
    private Address address;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Lorry_Driver",
                joinColumns = @JoinColumn(name = "driver_id"),
                inverseJoinColumns = @JoinColumn(name = "lorry_id"))
    public Set<Lorry> lorrySet = new HashSet<>();

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

    public String getChildrenDetails() {
        return childrenDetails;
    }

    public void setChildrenDetails(String childrenDetails) {
        this.childrenDetails = childrenDetails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Lorry> getLorrySet() {
        return lorrySet;
    }

    public void setLorrySet(Set<Lorry> lorrySet) {
        this.lorrySet = lorrySet;
    }
}
