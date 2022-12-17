package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Address;
import com.transport.sabi.api.domain.Driver;
import com.transport.sabi.api.v1.model.DriverDto;
import liquibase.pro.packaged.A;
import liquibase.pro.packaged.D;
import org.springframework.stereotype.Component;

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
        driverDto.setChildrenDetails(String.valueOf(objects[3]));
        Address address = new Address();
        address.setAddress(String.valueOf(objects[4]));
        address.setCity(String.valueOf(objects[5]));
        address.setState(String.valueOf(objects[6]));
        address.setZipcode(String.valueOf(objects[7]));
        driverDto.setAddress(address);
        if(objects[7] != null && objects[8] != null) {
            driverDto.setLorryId(Long.valueOf(String.valueOf(objects[8])));
            driverDto.setNumberPlate(String.valueOf(objects[9]));
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
        driver.setChildrenDetails(driverDto.getChildrenDetails());
        driver.setName(driverDto.getDriverName());

        return driver;
    }

    @Override
    public DriverDto driverToDriverDto(Driver driver) {

        if(driver == null) return null;

        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setChildrenDetails(driver.getChildrenDetails());
        driverDto.setDriverName(driver.getName());
        driverDto.setAddress(driver.getAddress());
        driverDto.setDob(driver.getDob());

        return driverDto;
    }
}
