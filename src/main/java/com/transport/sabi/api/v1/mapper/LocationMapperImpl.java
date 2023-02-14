package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Location;
import com.transport.sabi.api.v1.model.location.LocationDtoPost;
import com.transport.sabi.api.v1.model.location.LocationTripDto;
import com.transport.sabi.api.v1.model.location.LocationsDTO;
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

    @Override
    public LocationTripDto getLocationTrip(Object[] objects) {
        if(objects == null) return null;

        LocationTripDto locationTripDto = new LocationTripDto();
        locationTripDto.setId(Long.valueOf(String.valueOf(objects[0])));
        locationTripDto.setDistributorName(String.valueOf(objects[1]));
        locationTripDto.setCity(String.valueOf(objects[2]));

        return locationTripDto;
    }

    @Override
    public LocationTripDto locationToLocationTripDto(Location location) {
        if(location == null) return null;

        LocationTripDto locationTripDto = new LocationTripDto();
        locationTripDto.setId(location.getId());
        locationTripDto.setCity(location.getAddress().getCity());
        locationTripDto.setDistributorName(location.getDistributorName());

        return locationTripDto;
    }
}
