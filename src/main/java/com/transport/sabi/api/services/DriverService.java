package com.transport.sabi.api.services;

import com.transport.sabi.api.v1.model.DriverDto;

import java.util.List;

public interface DriverService {
    List<DriverDto> getDriversNameAndLorry();
    List<DriverDto> getDriversWithLorry();
    DriverDto save(DriverDto driverDto);
    DriverDto update(DriverDto driverDto);
    DriverDto getLorryDtoById(Long id);
}
