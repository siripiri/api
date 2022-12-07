package com.transport.sabi.api.dao;

import com.transport.sabi.api.v1.model.LocationDtoPost;
import com.transport.sabi.api.v1.model.LorryWithDriverNameDto;

import java.util.List;

public interface QueryDao {
    int updateLocation(LocationDtoPost locationDtoPost, Long id);
    List<Object[]> getAllLorryAndDriverName();
    List<Object[]> getDriversNameAndLorry();
}
