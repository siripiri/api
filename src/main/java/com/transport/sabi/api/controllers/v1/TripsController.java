package com.transport.sabi.api.controllers.v1;

import com.transport.sabi.api.repository.TripDetailRepository;
import com.transport.sabi.api.services.TripService;
import com.transport.sabi.api.v1.model.trips.TripDto;
import com.transport.sabi.api.v1.model.trips.TripsTableDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trips")
public class TripsController {
    private final TripService tripService;
    private final TripDetailRepository tripDetailRepository;

    public TripsController(TripService tripService,
                           TripDetailRepository tripDetailRepository) {
        this.tripService = tripService;
        this.tripDetailRepository = tripDetailRepository;
    }

    @GetMapping(value = "/tripsTable", produces = "application/json")
    public ResponseEntity<List<TripsTableDto>> getAllTripsForTable() {
        return new ResponseEntity<>(tripService.getAllTrip(), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TripDto> saveTripDto(@RequestBody TripDto tripDto) {
        return new ResponseEntity<>(tripService.saveTrip(tripDto), HttpStatus.OK);
    }
}
