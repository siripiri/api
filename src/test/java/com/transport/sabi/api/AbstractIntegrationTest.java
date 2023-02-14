package com.transport.sabi.api;

import com.transport.sabi.api.domain.*;
import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.domain.driver.EmergencyContact;
import com.transport.sabi.api.domain.driver.FamilyInformation;
import com.transport.sabi.api.domain.driver.PersonalInformation;
import com.transport.sabi.api.domain.repository.*;
import com.transport.sabi.api.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AbstractIntegrationTest {

    @Autowired
    public PersonalInformationRepository personalInformationRepository;

    @Autowired
    public EmergencyContactRepository emergencyContactRepository;

    @Autowired
    public FamilyInformationRepository familyInformationRepository;

    @Autowired
    public DriverRepository driverRepository;

    @Autowired
    public LorryRepository lorryRepository;

    public Lorry loadLorry() {
        Lorry lorry = new Lorry();
        lorry.setType("lorry");
        lorry.setModelNumber("4.4.4.4");
        lorry.setNumberPlate("TN 45 K 1244");
        lorry.setManufacturer("BMW");

        return lorry;
    }

    public PersonalInformation loadPersonalInformation() {
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setDriverLicence("1234 1234 12234 1234");
        personalInformation.setAadharNo("1234 1234 1234 1234");
        personalInformation.setChildren(3);
        personalInformation.setNationality("Paradise Island");
        personalInformation.setReligion("Christian");
        personalInformation.setMartialStatus("Single");
        personalInformation.setEmploymentOfSpouse("Not Applicable");
        personalInformation.setWhatsappNo("7558174283");
        return personalInformation;
    }

    public EmergencyContact loadEmergencyContact() {
        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setName("Mikasa");
        emergencyContact.setRelationShip("Girl friend");
        emergencyContact.setPhoneNumber("+91 9629487394");
        return emergencyContact;
    }

    public FamilyInformation loadFamilyInformation() {
        FamilyInformation familyInformation = new FamilyInformation();
        familyInformation.setRelationShip("father");
        familyInformation.setName("Grisha Yeager");
        familyInformation.setDob("24/05/1988");
        return familyInformation;
    }

    public Driver loadDriver() throws ParseException {
        Driver driver = new Driver();
        driver.setDob("24/05/1999");
        Address address = new Address();
        address.setZipcode("638316");
        address.setAddress("chithode");
        address.setCity("erode");
        address.setState("Tamil Nadu");
        driver.setAddress(address);
        driver.setGender("male");
        driver.setPhoneNumber1("+91 7558174283");
        driver.setPhoneNumber2("+91 7558174283");
        driver.setName("Eren Yegar");

        PersonalInformation personalInformation = loadPersonalInformation();
        EmergencyContact emergencyContact = loadEmergencyContact();
        FamilyInformation familyInformation = loadFamilyInformation();

        personalInformation.setDriver(driver);

        driver.setPersonalInformation(personalInformation);
        driver.addFamilyInformation(familyInformation);
        driver.addEmergencyContact(emergencyContact);

        return driver;
    }

    public void setup() throws ParseException {
        Driver driver = loadDriver();
        driverRepository.save(driver);
    }

    @Test
    public void testDriverPersist() {
        Driver driver = driverRepository.findById(1L).orElse(null);
        PersonalInformation personalInformation = personalInformationRepository.findById(1L).orElse(null);
        EmergencyContact emergencyContact = emergencyContactRepository.findById(1L).orElse(null);

        assertNotNull(driver);
        assertNotNull(personalInformation);
        assertNotNull(emergencyContact);

        assertEquals(driver.getPersonalInformation().getId(), personalInformation.getId());
        assertEquals(driver.getId(), personalInformation.getDriver().getId());
        assertNotNull(emergencyContact.getDriver());
    }

}
