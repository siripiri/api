package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.TripDetail;
import com.transport.sabi.api.v1.model.trips.TripDto;
import com.transport.sabi.api.v1.model.trips.TripsTableDto;

public interface TripsMapper {
    TripsTableDto objectsToTripsTableDto(Object[] objects);
    TripDetail tripDtoToTripDetail(TripDto tripDto);
    TripDto tripDetailToTripDto(TripDetail tripDetail);
}
