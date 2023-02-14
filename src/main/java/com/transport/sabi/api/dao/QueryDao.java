package com.transport.sabi.api.dao;

import com.transport.sabi.api.v1.model.location.LocationDtoPost;
import com.transport.sabi.api.v1.model.location.LocationTripDto;

import java.util.List;

public interface QueryDao {
    int updateLocation(LocationDtoPost locationDtoPost, Long id);
    List<Object[]> getAllLorryAndDriverName();
    List<Object[]> getDriversNameAndLorry();
    int insertAssignDriver(Long driverId, Long lorryId);
    List<Object[]> getDriversWithLorry();
    List<Object[]> getAllTripTable();
    List<Object[]> getAllLorryNameAndId();
    List<Object[]> getAllLocationTrip();
    Object getLorryAndDriverByLorryId(Long id);
}
