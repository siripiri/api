package com.transport.sabi.api.domain.driver;

import com.transport.sabi.api.domain.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Driver extends BaseEntity {
    private String name;
    private String dob;
    private String gender;
    private String phoneNumber1;
    private String phoneNumber2;
    @Embedded
    private Address address;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Lorry_Driver",
                joinColumns = @JoinColumn(name = "driver_id"),
                inverseJoinColumns = @JoinColumn(name = "lorry_id"))
    public Set<Lorry> lorrySet = new HashSet<>();

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private Set<EmergencyContact> emergencyContacts;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FamilyInformation> familyInformations;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PersonalInformation personalInformation;


    public void addEmergencyContact(EmergencyContact emergencyContact){
        if(emergencyContacts == null){
            emergencyContacts = new HashSet<>();
        }
        emergencyContacts.add(emergencyContact);
        emergencyContact.setDriver(this);
    }

    public void addFamilyInformation(FamilyInformation familyInformation){
        if(familyInformations == null){
            familyInformations = new HashSet<>();
        }
        familyInformations.add(familyInformation);
        familyInformation.setDriver(this);
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public Set<Lorry> getLorrySet() {
        return lorrySet;
    }

    public void setLorrySet(Set<Lorry> lorrySet) {
        this.lorrySet = lorrySet;
    }

    public Set<EmergencyContact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(Set<EmergencyContact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public Set<FamilyInformation> getFamilyInformations() {
        return familyInformations;
    }

    public void setFamilyInformations(Set<FamilyInformation> familyInformations) {
        this.familyInformations = familyInformations;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }
}
