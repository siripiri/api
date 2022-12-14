package com.transport.sabi.api.dao;

import com.transport.sabi.api.v1.model.LocationDtoPost;

import java.util.List;

public interface QueryDao {
    int updateLocation(LocationDtoPost locationDtoPost, Long id);
    List<Object[]> getAllLorryAndDriverName();
    List<Object[]> getDriversNameAndLorry();
    int insertAssignDriver(Long driverId, Long lorryId);
}
