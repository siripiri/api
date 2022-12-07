package com.transport.sabi.api.services;

import com.transport.sabi.api.dao.QueryDao;
import com.transport.sabi.api.domain.Location;
import com.transport.sabi.api.domain.repository.LocationRepository;
import com.transport.sabi.api.services.exception.ResourceNotFoundException;
import com.transport.sabi.api.v1.mapper.LocationMapper;
import com.transport.sabi.api.v1.model.LocationDtoPost;
import com.transport.sabi.api.v1.model.LocationsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final QueryDao queryDao;

    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper, QueryDao queryDao) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.queryDao = queryDao;
    }

    @Override
    public List<LocationsDTO> getAllLocationsDTO() {
        return locationRepository.findAll()
                .stream()
                .map(locationMapper::locationToLocationDTO)
                .map(locationsDTO -> {
                    locationsDTO.setUrl("/api/v1/location/" + locationsDTO.getId());
                    return locationsDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public LocationsDTO getLocationDTOById(Long id) {
        return locationRepository.findById(id)
                .map(locationMapper::locationToLocationDTO)
                .map(locationsDTO -> {
                    locationsDTO.setUrl("/api/v1/location/" + locationsDTO.getId());
                    return locationsDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public LocationsDTO saveLocationDto(LocationDtoPost locationDtoPost) {
        Location location = locationRepository.saveAndFlush(locationMapper.locationDtoPostToLocation(locationDtoPost));

        LocationsDTO locationsDTO = locationMapper.locationToLocationDTO(location);
        locationsDTO.setUrl("/api/v1/location/" + location.getId());

        return locationsDTO;
    }

    @Override
    public LocationsDTO updateLocationDtoPost(LocationDtoPost locationDtoPost, Long id) {
        queryDao.updateLocation(locationDtoPost, id);
        return this.getLocationDTOById(id);
    }

    @Override
    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }
}
