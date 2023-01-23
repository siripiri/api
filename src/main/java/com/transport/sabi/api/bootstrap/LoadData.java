package com.transport.sabi.api.bootstrap;

import com.transport.sabi.api.domain.*;
import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.domain.driver.EmergencyContact;
import com.transport.sabi.api.domain.driver.FamilyInformation;
import com.transport.sabi.api.domain.driver.PersonalInformation;
import com.transport.sabi.api.domain.expenses.Expenses;
import com.transport.sabi.api.domain.expenses.ExpensesCategory;
import com.transport.sabi.api.domain.expenses.Fuel;
import com.transport.sabi.api.domain.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class LoadData implements CommandLineRunner {

    private final LocationRepository locationRepository;
    private final LorryRepository lorryRepository;
    private final DriverRepository driverRepository;
    private final PersonalInformationRepository personalInformationRepository;
    private final ExpensesRespository expensesRespository;
    private final ExpensesCategoryRepository expensesCategoryRepository;

    private final FuelRepository fuelRepository;

    public LoadData(LocationRepository locationRepository, LorryRepository lorryRepository, DriverRepository driverRepository, PersonalInformationRepository personalInformationRepository, ExpensesRespository expensesRespository, ExpensesCategoryRepository expensesCategoryRepository, FuelRepository fuelRepository) {
        this.locationRepository = locationRepository;
        this.lorryRepository = lorryRepository;
        this.driverRepository = driverRepository;
        this.personalInformationRepository = personalInformationRepository;
        this.expensesRespository = expensesRespository;
        this.expensesCategoryRepository = expensesCategoryRepository;
        this.fuelRepository = fuelRepository;
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

    public List<ExpensesCategory> loadExpensesCategory() {
        ExpensesCategory expensesCategory1 = new ExpensesCategory();
        expensesCategory1.setName("Repair");

        ExpensesCategory expensesCategory2 = new ExpensesCategory();
        expensesCategory2.setName("Fuel");

        return List.of(expensesCategory1, expensesCategory2);
    }

    public List<Expenses> loadExpenses() {
        Expenses expenses1 = new Expenses();
        expenses1.setName("Fuel");
        expenses1.setAmount("1000.00");
        expenses1.setDate("24/05/1999");
        expenses1.setNotes("Hello World");
        ExpensesCategory expensesCategory = expensesCategoryRepository.findByName("Fuel").orElse(null);
        expenses1.setExpensesCategory(expensesCategory);

        Expenses expenses2 = new Expenses();
        expenses2.setName("Fuel");
        expenses2.setAmount("1000.00");
        expenses2.setDate("24/05/1999");
        expenses2.setNotes("Hello World");
        expensesCategory = expensesCategoryRepository.findByName("Repair").orElse(null);
        expenses2.setExpensesCategory(expensesCategory);

        return List.of(expenses1, expenses2);
    }

    public Fuel loadFuelExpenses() {
        Expenses expenses1 = new Expenses();
        expenses1.setName("Fuel");
        expenses1.setAmount("500.00");
        expenses1.setDate("24/05/1999");
        expenses1.setNotes("Hello World");
        ExpensesCategory expensesCategory = expensesCategoryRepository.findByName("Fuel").orElse(null);
        expenses1.setExpensesCategory(expensesCategory);

        Expenses saved = expensesRespository.saveAndFlush(expenses1);

        Fuel fuel = new Fuel();
        fuel.setExpenses(saved);
        fuel.setCurrentPrice("100.00");
        fuel.setLiterFilled(5L);
        fuel.setPaymentMode("CARD");



        return fuel;
    }

    public void loadData() throws ParseException {
        Lorry lorry = loadLorry();
        Driver driver = loadDriver();

        lorry.getDrivers().add(driver);

        lorryRepository.save(lorry);
        driverRepository.save(driver);
        expensesCategoryRepository.saveAllAndFlush(loadExpensesCategory());
        expensesRespository.saveAllAndFlush(loadExpenses());
        fuelRepository.saveAndFlush(loadFuelExpenses());
    }

    @Override
    public void run(String... args) throws Exception {
        loadLocation();
        loadData();
    }
}
