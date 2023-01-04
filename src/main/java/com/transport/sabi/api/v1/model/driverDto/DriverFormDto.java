package com.transport.sabi.api.v1.model.driverDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transport.sabi.api.domain.Address;

import java.util.List;

public class DriverFormDto {
    private DriverDto profile;
    private Address address;
    private PersonalInformationDto personalInformation;
    private List<EmergencyContactDto> emergencyContacts;
    private List<FamilyInformationDto> familyInformations;

    @JsonProperty("api_url")
    public String url;

    public DriverDto getProfile() {
        return profile;
    }

    public void setProfile(DriverDto profile) {
        this.profile = profile;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PersonalInformationDto getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformationDto personalInformation) {
        this.personalInformation = personalInformation;
    }

    public List<EmergencyContactDto> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<EmergencyContactDto> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public List<FamilyInformationDto> getFamilyInformations() {
        return familyInformations;
    }

    public void setFamilyInformations(List<FamilyInformationDto> familyInformations) {
        this.familyInformations = familyInformations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
