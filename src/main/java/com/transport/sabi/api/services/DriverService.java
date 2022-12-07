package com.transport.sabi.api.services;

import com.transport.sabi.api.v1.model.DriverNamesWithLorryDto;

import java.util.List;

public interface DriverService {
    List<DriverNamesWithLorryDto> getDriversNameAndLorry();
}
