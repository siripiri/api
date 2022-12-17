package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Driver;
import com.transport.sabi.api.v1.model.DriverDto;

public interface DriverMapper {
    DriverDto driverNamesWithLorryId(Object[] objects);
    DriverDto driversWithLorry(Object[] objects);
    Driver driverDtoToDriver(DriverDto driverDto);
    DriverDto driverToDriverDto(Driver driver);
}
