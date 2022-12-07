package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Location;
import com.transport.sabi.api.v1.model.LocationDtoPost;
import com.transport.sabi.api.v1.model.LocationsDTO;

public interface LocationMapper {
    public LocationsDTO locationToLocationDTO(Location location);
    public Location locationDtoPostToLocation(LocationDtoPost locationDtoPost);
}
