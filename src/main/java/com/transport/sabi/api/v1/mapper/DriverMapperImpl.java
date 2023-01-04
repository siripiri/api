package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.*;
import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.domain.driver.EmergencyContact;
import com.transport.sabi.api.domain.driver.FamilyInformation;
import com.transport.sabi.api.domain.driver.PersonalInformation;
import com.transport.sabi.api.v1.model.driverDto.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DriverMapperImpl implements DriverMapper {
    @Override
    public DriverDto driverNamesWithLorryId(Object[] objects) {

        if(objects == null) {
            return null;
        }

        DriverDto driverDto =  new DriverDto();
        if(objects[0] != null) {
            driverDto.setLorryId(Long.valueOf(String.valueOf(objects[0])));
        }
        driverDto.setId(Long.valueOf(String.valueOf(objects[1])));
        driverDto.setDriverName(String.valueOf(objects[2]));
        return driverDto;
    }

    @Override
    public DriverDto driversWithLorry(Object[] objects) {

        if(objects == null) {
            return null;
        }

        DriverDto driverDto = new DriverDto();
        driverDto.setId(Long.valueOf(String.valueOf(objects[0])));
        driverDto.setDriverName(String.valueOf(objects[1]));
        driverDto.setDob(String.valueOf(objects[2]));
        driverDto.setGender(String.valueOf(objects[3]));
        driverDto.setPhoneNumber1(String.valueOf(objects[4]));
        driverDto.setPhoneNumber2(String.valueOf(objects[5]));
        Address address = new Address();
        address.setAddress(String.valueOf(objects[6]));
        address.setCity(String.valueOf(objects[7]));
        address.setState(String.valueOf(objects[8]));
        address.setZipcode(String.valueOf(objects[9]));
        driverDto.setAddress(address);
        if(objects[10] != null && objects[11] != null) {
            driverDto.setLorryId(Long.valueOf(String.valueOf(objects[10])));
            driverDto.setNumberPlate(String.valueOf(objects[11]));
        }
        return driverDto;
    }

    @Override
    public Driver driverDtoToDriver(DriverDto driverDto) {

        if(driverDto == null) {
            return null;
        }

        Driver driver = new Driver();
        driver.setAddress(driverDto.getAddress());
        driver.setDob(driverDto.getDob());
        driver.setGender(driverDto.getGender());
        driver.setPhoneNumber1(driverDto.getPhoneNumber1());
        driver.setPhoneNumber2(driverDto.getPhoneNumber2());
        driver.setName(driverDto.getDriverName());

        return driver;
    }

    @Override
    public DriverDto driverToDriverDto(Driver driver) {

        if(driver == null) return null;

        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setDriverName(driver.getName());
        driverDto.setGender(driver.getGender());
        driverDto.setPhoneNumber1(driver.getPhoneNumber1());
        driverDto.setPhoneNumber2(driver.getPhoneNumber2());
        driverDto.setAddress(driver.getAddress());
        driverDto.setDob(driver.getDob());

        return driverDto;
    }

    @Override
    public Driver driverFormDtoToDriver(DriverFormDto driverFormDto) {

        if(driverFormDto == null) return null;

        Driver driver = new Driver();
        driver.setName(driverFormDto.getProfile().getDriverName());
        driver.setGender(driverFormDto.getProfile().getGender());
        driver.setDob(driverFormDto.getProfile().getDob());
        driver.setPhoneNumber1(driverFormDto.getProfile().getPhoneNumber1());
        if(driverFormDto.getProfile().getPhoneNumber2() != null) {
            driver.setPhoneNumber2(driverFormDto.getProfile().getPhoneNumber2());
        }

        return driver;
    }

    @Override
    public PersonalInformation personalInformationDtoToPersonalInformation(PersonalInformationDto personalInformationDto) {

        if(personalInformationDto == null) return null;

        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setNationality(personalInformationDto.getNationality());
        personalInformation.setReligion(personalInformationDto.getReligion());
        personalInformation.setChildren(personalInformationDto.getChildren());
        personalInformation.setEmploymentOfSpouse(personalInformationDto.getEmploymentOfSpouse());
        personalInformation.setMartialStatus(personalInformationDto.getMartialStatus());
        personalInformation.setWhatsappNo(personalInformationDto.getWhatsappNo());
        personalInformation.setDriverLicence(personalInformationDto.getDriverLicence());
        personalInformation.setAadharNo(personalInformationDto.getAadharCard());

        return  personalInformation;
    }

    @Override
    public EmergencyContact emergencyContactDtoToEmergencyContact(EmergencyContactDto emergencyContactDto) {

        if(emergencyContactDto == null) return null;

        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setName(emergencyContactDto.getName());
        emergencyContact.setRelationShip(emergencyContactDto.getRelationShip());
        emergencyContact.setPhoneNumber(emergencyContactDto.getPhoneNumber());

        return emergencyContact;
    }

    @Override
    public FamilyInformation familyInformationDtoToFamilyInformation(FamilyInformationDto familyInformationDto) {

        if(familyInformationDto == null) return null;

        FamilyInformation familyInformation = new FamilyInformation();
        familyInformation.setName(familyInformationDto.getName());
        familyInformation.setDob(familyInformationDto.getDob());
        familyInformation.setRelationShip(familyInformationDto.getRelationShip());

        return familyInformation;
    }

    @Override
    public DriverFormDto driverToDriverFormDto(Driver driver) {
        if(driver == null) return null;

        DriverFormDto driverFormDto = new DriverFormDto();
        driverFormDto.setProfile(driverToDriverDto(driver));
        driverFormDto.setPersonalInformation(personalInformationToPersonalInformationDto(driver.getPersonalInformation()));

        driverFormDto.setEmergencyContacts(
                driver.getEmergencyContacts()
                        .stream()
                        .map(this::emergencyContactToEmergencyContactDto)
                        .collect(Collectors.toList())
        );

        driverFormDto.setFamilyInformations(
                driver.getFamilyInformations()
                        .stream()
                        .map(this::familyInformationToFamilyInformationDto)
                        .collect(Collectors.toList())
        );

        return driverFormDto;
    }

    public PersonalInformationDto personalInformationToPersonalInformationDto(PersonalInformation personalInformation) {
        if(personalInformation == null) return null;

        PersonalInformationDto personalInformationDto = new PersonalInformationDto();
        personalInformationDto.setId(personalInformation.getId());
        personalInformationDto.setAadharCard(personalInformation.getAadharNo());
        personalInformationDto.setChildren(personalInformation.getChildren());
        personalInformationDto.setDriverLicence(personalInformation.getDriverLicence());
        personalInformationDto.setNationality(personalInformation.getNationality());
        personalInformationDto.setReligion(personalInformation.getReligion());
        personalInformationDto.setMartialStatus(personalInformation.getMartialStatus());
        personalInformationDto.setWhatsappNo(personalInformation.getWhatsappNo());
        personalInformationDto.setEmploymentOfSpouse(personalInformation.getEmploymentOfSpouse());
        personalInformationDto.setDiverId(personalInformation.getDriver().getId());
        return personalInformationDto;
    }

    public FamilyInformationDto familyInformationToFamilyInformationDto(FamilyInformation familyInformation) {
        if(familyInformation == null) return null;

        FamilyInformationDto familyInformationDto = new FamilyInformationDto();
        familyInformationDto.setId(familyInformation.getId());
        familyInformationDto.setDriverId(familyInformation.getDriver().getId());
        familyInformationDto.setName(familyInformation.getName());
        familyInformationDto.setRelationShip(familyInformation.getRelationShip());
        familyInformationDto.setDob(familyInformation.getDob());
        return familyInformationDto;
    }

    public EmergencyContactDto emergencyContactToEmergencyContactDto(EmergencyContact emergencyContact) {
        if(emergencyContact == null) return null;

        EmergencyContactDto emergencyContactDto = new EmergencyContactDto();
        emergencyContactDto.setDriverId(emergencyContact.getDriver().getId());
        emergencyContactDto.setId(emergencyContact.getId());
        emergencyContactDto.setName(emergencyContact.getName());
        emergencyContactDto.setPhoneNumber(emergencyContact.getPhoneNumber());
        emergencyContactDto.setRelationShip(emergencyContact.getRelationShip());
        return emergencyContactDto;
    }
}
