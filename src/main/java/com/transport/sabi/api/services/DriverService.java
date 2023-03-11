package com.transport.sabi.api.services;

import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.v1.model.driverDto.DriverDto;
import com.transport.sabi.api.v1.model.driverDto.DriverFormDto;
import com.transport.sabi.api.v1.model.driverDto.DriverNameDto;

import java.util.List;

public interface DriverService {
    List<DriverDto> getDriversNameAndLorry();
    List<DriverDto> getDriversWithLorry();
    DriverDto save(DriverDto driverDto);
    DriverDto update(DriverDto driverDto);
    DriverDto getLorryDtoById(Long id);
    DriverFormDto saveDriverFormDto(DriverFormDto driverFormDto);
    DriverFormDto getDriverFormDtoById(Long id);
    List<DriverNameDto> getAllDriverName();
}
