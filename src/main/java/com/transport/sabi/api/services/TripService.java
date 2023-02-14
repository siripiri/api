package com.transport.sabi.api.services;

import com.transport.sabi.api.domain.TripDetail;
import com.transport.sabi.api.v1.model.trips.TripDto;
import com.transport.sabi.api.v1.model.trips.TripsTableDto;

import java.util.List;

public interface TripService {
    List<TripsTableDto> getAllTrip();
    TripDto saveTrip(TripDto tripDto);
}
