package com.transport.sabi.api.services;

import com.transport.sabi.api.v1.model.location.LocationDtoPost;
import com.transport.sabi.api.v1.model.location.LocationTripDto;
import com.transport.sabi.api.v1.model.location.LocationsDTO;

import java.util.List;

public interface LocationService {
    List<LocationsDTO> getAllLocationsDTO();
    LocationsDTO getLocationDTOById(Long id);
    LocationsDTO saveLocationDto(LocationDtoPost locationDtoPost);
    LocationsDTO updateLocationDtoPost(LocationDtoPost locationDtoPost, Long id);
    void deleteLocationById(Long id);
    List<LocationTripDto> getAllLocationTrip();
}
