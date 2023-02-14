package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Location;
import com.transport.sabi.api.v1.model.location.LocationDtoPost;
import com.transport.sabi.api.v1.model.location.LocationTripDto;
import com.transport.sabi.api.v1.model.location.LocationsDTO;

public interface LocationMapper {
    LocationsDTO locationToLocationDTO(Location location);
    Location locationDtoPostToLocation(LocationDtoPost locationDtoPost);
    LocationTripDto getLocationTrip(Object[] objects);
    LocationTripDto locationToLocationTripDto(Location location);
}
