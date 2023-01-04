package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.domain.driver.EmergencyContact;
import com.transport.sabi.api.domain.driver.FamilyInformation;
import com.transport.sabi.api.domain.driver.PersonalInformation;
import com.transport.sabi.api.v1.model.driverDto.*;

public interface DriverMapper {
    DriverDto driverNamesWithLorryId(Object[] objects);
    DriverDto driversWithLorry(Object[] objects);
    Driver driverDtoToDriver(DriverDto driverDto);
    DriverDto driverToDriverDto(Driver driver);
    Driver driverFormDtoToDriver(DriverFormDto driverFormDto);
    PersonalInformation personalInformationDtoToPersonalInformation(PersonalInformationDto personalInformationDto);
    EmergencyContact emergencyContactDtoToEmergencyContact(EmergencyContactDto emergencyContactDto);
    FamilyInformation familyInformationDtoToFamilyInformation(FamilyInformationDto familyInformationDto);
    DriverFormDto driverToDriverFormDto(Driver driver);

}
