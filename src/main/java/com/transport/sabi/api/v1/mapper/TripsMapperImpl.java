package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.TripDetail;
import com.transport.sabi.api.repository.FamilyInformationRepository;
import com.transport.sabi.api.v1.model.location.LocationTripDto;
import com.transport.sabi.api.v1.model.lorry.LorryIdPlateDto;
import com.transport.sabi.api.v1.model.trips.TripDto;
import com.transport.sabi.api.v1.model.trips.TripsTableDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

@Component
public class TripsMapperImpl implements TripsMapper {

    private final LorryMapper lorryMapper;
    private final LocationMapper locationMapper;

    public TripsMapperImpl(LorryMapper lorryMapper, LocationMapper locationMapper) {
        this.lorryMapper = lorryMapper;
        this.locationMapper = locationMapper;
    }

    @Override
    public TripsTableDto objectsToTripsTableDto(Object[] objects) {
        if(objects == null) return null;

        TripsTableDto tripsTableDto = new TripsTableDto();
        tripsTableDto.setId(Long.valueOf(String.valueOf(objects[0])));
        tripsTableDto.setDate(String.valueOf(objects[1]));

        Long plantToDistributor = Long.valueOf(String.valueOf(objects[2]));
        Long distributorToPlant = Long.valueOf(String.valueOf(objects[3]));
        tripsTableDto.setKmCovered(distributorToPlant - plantToDistributor);


        DateTimeFormatter format = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime time1 = LocalTime.parse(String.valueOf(objects[4]), format);
        LocalTime time2 = LocalTime.parse(String.valueOf(objects[5]), format);
        Duration duration1 = Duration.between(time1, time2);
        LocalTime time3 = LocalTime.parse(String.valueOf(objects[6]), format);
        LocalTime time4 = LocalTime.parse(String.valueOf(objects[7]), format);
        Duration duration2 = Duration.between(time3, time4);
        Duration duration = duration1.plus(duration2);
        tripsTableDto.setTimeTaken(String.valueOf(duration.toMinutes()));

        tripsTableDto.setLocationId(Long.valueOf(String.valueOf(objects[8])));
        tripsTableDto.setDistributorName(String.valueOf(objects[9]));
        tripsTableDto.setCity(String.valueOf(objects[10]));
        tripsTableDto.setKmAllocated(Long.valueOf(String.valueOf(objects[11])));

        tripsTableDto.setLorryId(Long.valueOf(String.valueOf(objects[12])));
        tripsTableDto.setLorryNumberPlate(String.valueOf(objects[13]));

        tripsTableDto.setDriverId(Long.valueOf(String.valueOf(objects[14])));
        tripsTableDto.setDriverName(String.valueOf(objects[15]));

        return tripsTableDto;
    }

    @Override
    public TripDetail tripDtoToTripDetail(TripDto tripDto) {
        if(tripDto == null) return null;

        TripDetail tripDetail = new TripDetail();
        tripDetail.setDate(tripDto.getDate());
        tripDetail.setPlantToDistributor(tripDto.getPlantToDistributor());
        tripDetail.setDistributorToPlant(tripDto.getDistributorToPlant());
        tripDetail.setPlantStart(tripDto.getPlantStart());
        tripDetail.setDesEnd(tripDto.getDesEnd());
        tripDetail.setDesStart(tripDto.getDesStart());
        tripDetail.setPlantEnd(tripDto.getPlantEnd());

        return tripDetail;
    }

    @Override
    public TripDto tripDetailToTripDto(TripDetail tripDetail) {
        if(tripDetail == null) return null;

        TripDto tripDto = new TripDto();
        tripDto.setId(tripDetail.getId());
        tripDto.setDate(tripDetail.getDate());
        tripDto.setPlantToDistributor(tripDetail.getPlantToDistributor());
        tripDto.setDistributorToPlant(tripDetail.getDistributorToPlant());
        tripDto.setPlantStart(tripDetail.getPlantStart());
        tripDto.setDesEnd(tripDetail.getDesEnd());
        tripDto.setPlantEnd(tripDetail.getDesEnd());
        tripDto.setDesStart(tripDetail.getDesStart());

        LocationTripDto locationTripDto = locationMapper.locationToLocationTripDto(tripDetail.getLocation());
        tripDto.setLocation(locationTripDto);

        LorryIdPlateDto lorryIdPlateDto = lorryMapper.lorryToLorryIdPlateDto(tripDetail.getLorry());
        tripDto.setLorry(lorryIdPlateDto);

        return  tripDto;
    }
}
