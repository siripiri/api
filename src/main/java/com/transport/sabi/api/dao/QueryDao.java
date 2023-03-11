package com.transport.sabi.api.dao;

import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.v1.model.driverDto.DriverNameDto;
import com.transport.sabi.api.v1.model.location.LocationDtoPost;
import com.transport.sabi.api.v1.model.location.LocationTripDto;
import com.transport.sabi.api.v1.model.lorry.LorryIdPlateDto;

import java.util.List;

public interface QueryDao {
    int updateLocation(LocationDtoPost locationDtoPost, Long id);
    List<Object[]> getAllLorryAndDriverName();
    List<Object[]> getDriversNameAndLorry();
    int insertAssignDriver(Long driverId, Long lorryId);
    List<Object[]> getDriversWithLorry();
    List<Object[]> getAllTripTable();
    List<LorryIdPlateDto> getAllLorryNameAndId();
    List<Object[]> getAllLocationTrip();
    Object getLorryAndDriverByLorryId(Long id);
    List<DriverNameDto> getAllDriverName();
}
