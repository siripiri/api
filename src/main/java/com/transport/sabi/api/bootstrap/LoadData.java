package com.transport.sabi.api.bootstrap;

import com.transport.sabi.api.domain.Address;
import com.transport.sabi.api.domain.Driver;
import com.transport.sabi.api.domain.Location;
import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.domain.repository.DriverRepository;
import com.transport.sabi.api.domain.repository.LocationRepository;
import com.transport.sabi.api.domain.repository.LorryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Component
public class LoadData implements CommandLineRunner {

    private final LocationRepository locationRepository;
    private final LorryRepository lorryRepository;
    private final DriverRepository driverRepository;

    public LoadData(LocationRepository locationRepository, LorryRepository lorryRepository, DriverRepository driverRepository) {
        this.locationRepository = locationRepository;
        this.lorryRepository = lorryRepository;
        this.driverRepository = driverRepository;
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
        driver.setDob(this.getDate("24/05/1999"));
        Address address = new Address();
        address.setZipcode("638316");
        address.setAddress("chithode");
        address.setCity("erode");
        address.setState("Tamil Nadu");
        driver.setAddress(address);
        driver.setChildrenDetails("1 boy 1 girl");
        driver.setName("Eren Yegar");
        return driver;
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
