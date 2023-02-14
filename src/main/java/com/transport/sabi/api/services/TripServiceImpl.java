package com.transport.sabi.api.services;

import com.transport.sabi.api.dao.QueryDao;
import com.transport.sabi.api.domain.Location;
import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.domain.TripDetail;
import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.repository.DriverRepository;
import com.transport.sabi.api.repository.LocationRepository;
import com.transport.sabi.api.repository.LorryRepository;
import com.transport.sabi.api.repository.TripDetailRepository;
import com.transport.sabi.api.services.exception.ResourceNotFoundException;
import com.transport.sabi.api.v1.mapper.TripsMapper;
import com.transport.sabi.api.v1.model.trips.TripDto;
import com.transport.sabi.api.v1.model.trips.TripsTableDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService{

    private final TripDetailRepository tripDetailRepository;
    private final QueryDao queryDao;
    private final TripsMapper tripsMapper;
    private final LocationRepository locationRepository;
    private final LorryRepository lorryRepository;
    private final DriverRepository driverRepository;

    public TripServiceImpl(TripDetailRepository tripDetailRepository, QueryDao queryDao, TripsMapper tripsMapper,
                           LocationRepository locationRepository,
                           LorryRepository lorryRepository,
                           DriverRepository driverRepository) {
        this.tripDetailRepository = tripDetailRepository;
        this.queryDao = queryDao;
        this.tripsMapper = tripsMapper;
        this.locationRepository = locationRepository;
        this.lorryRepository = lorryRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public List<TripsTableDto> getAllTrip() {
        return queryDao.getAllTripTable()
                .stream()
                .map(tripsMapper::objectsToTripsTableDto)
                .peek(tripsTableDto -> tripsTableDto.setUrl("/api/v1/trips/tripsTable/" + tripsTableDto.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public TripDto saveTrip(TripDto tripDto) {
        Location location = locationRepository.findById(tripDto.getLocation().getId())
                .orElseThrow(ResourceNotFoundException::new);

        Lorry lorry = lorryRepository.findById(tripDto.getLorry().getId())
                .orElseThrow(ResourceNotFoundException::new);

        Driver driver = driverRepository.findById(Long.valueOf(String.valueOf(queryDao.getLorryAndDriverByLorryId(tripDto.getLorry().getId())))).orElse(null);

        TripDetail tripDetail = tripsMapper.tripDtoToTripDetail(tripDto);
        tripDetail.setLocation(location);
        tripDetail.setLorry(lorry);
        tripDetail.setDriver(driver);

        TripDetail savedTripDetail = tripDetailRepository.saveAndFlush(tripDetail);

        return tripsMapper.tripDetailToTripDto(savedTripDetail);
    }
}
