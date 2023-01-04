package com.transport.sabi.api.bootstrap;

import com.transport.sabi.api.domain.*;
import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.domain.driver.EmergencyContact;
import com.transport.sabi.api.domain.driver.FamilyInformation;
import com.transport.sabi.api.domain.driver.PersonalInformation;
import com.transport.sabi.api.domain.repository.DriverRepository;
import com.transport.sabi.api.domain.repository.LocationRepository;
import com.transport.sabi.api.domain.repository.LorryRepository;
import com.transport.sabi.api.domain.repository.PersonalInformationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LoadData implements CommandLineRunner {

    private final LocationRepository locationRepository;
    private final LorryRepository lorryRepository;
    private final DriverRepository driverRepository;
    private final PersonalInformationRepository personalInformationRepository;

    public LoadData(LocationRepository locationRepository, LorryRepository lorryRepository, DriverRepository driverRepository,
                    PersonalInformationRepository personalInformationRepository) {
        this.locationRepository = locationRepository;
        this.lorryRepository = lorryRepository;
        this.driverRepository = driverRepository;
        this.personalInformationRepository = personalInformationRepository;
    }

    public void loadLocation() {
        Location location = new Location();
        location.setKmAllocated(10L);
        Address address = new Address();
        address.setZipcode("638316");
        address.setAddress("chithode");
        address.setCity("erode");
        address.setState("Tamil Nadu");
        location.setAddress(address);
        location.setDistributorName("siri");

        locationRepository.saveAndFlush(location);
    }

    public Lorry loadLorry() {
        Lorry lorry = new Lorry();
        lorry.setType("lorry");
        lorry.setModelNumber("4.4.4.4");
        lorry.setNumberPlate("TN 45 K 1244");
        lorry.setManufacturer("BMW");

        return lorry;
    }

    private Timestamp getDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = dateFormat.parse(date);
        long time = date1.getTime();
        return new Timestamp(time);
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

        driver.setPersonalInformation(personalInformation);
        driver.addEmergencyContact(emergencyContact);
        driver.addFamilyInformation(familyInformation);

        personalInformation.setDriver(driver);

        return driver;
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

    public void loadData() throws ParseException {
        Lorry lorry = loadLorry();
        Driver driver = loadDriver();

        lorry.getDrivers().add(driver);

        lorryRepository.save(lorry);
        driverRepository.save(driver);
    }

    @Override
    public void run(String... args) throws Exception {
        loadLocation();
        loadData();
    }
}
