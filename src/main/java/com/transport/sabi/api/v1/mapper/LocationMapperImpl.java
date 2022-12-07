package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Location;
import com.transport.sabi.api.v1.model.LocationDtoPost;
import com.transport.sabi.api.v1.model.LocationsDTO;
import org.springframework.stereotype.Component;

@Component
public class LocationMapperImpl implements LocationMapper {
    @Override
    public LocationsDTO locationToLocationDTO(Location location) {
        if(location == null) {
            return null;
        }

        LocationsDTO locationsDTO = new LocationsDTO();
        locationsDTO.setId(location.getId());
        locationsDTO.setAddress(location.getAddress());
        locationsDTO.setKmAllocated(location.getKmAllocated());
        locationsDTO.setDistributorName(location.getDistributorName());

        return locationsDTO;
    }

    @Override
    public Location locationDtoPostToLocation(LocationDtoPost locationDtoPost) {
        if(locationDtoPost == null) {
            return null;
        }

        Location location = new Location();
        location.setDistributorName(locationDtoPost.getDistributorName());
        location.setAddress(locationDtoPost.getAddress());
        location.setKmAllocated(locationDtoPost.getKmAllocated());

        return location;
    }
}
