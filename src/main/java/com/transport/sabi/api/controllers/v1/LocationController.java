package com.transport.sabi.api.controllers.v1;

import com.transport.sabi.api.controllers.RestResponseEntityExceptionHandler;
import com.transport.sabi.api.services.LocationService;
import com.transport.sabi.api.v1.model.location.LocationDtoPost;
import com.transport.sabi.api.v1.model.location.LocationTripDto;
import com.transport.sabi.api.v1.model.location.LocationsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("api/v1/location")
@CrossOrigin(maxAge = 3600)
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<LocationsDTO>> getLocations() {
        return new ResponseEntity<List<LocationsDTO>>(locationService.getAllLocationsDTO(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getLocationById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(locationService.getLocationDTOById(Long.valueOf(id)), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new RestResponseEntityExceptionHandler().handleBadRequest(e);
        }
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody ResponseEntity<LocationsDTO> postLocationDto(@RequestBody LocationDtoPost locationDtoPost) {
        LocationsDTO savedLocationsDto = locationService.saveLocationDto(locationDtoPost);
        return new ResponseEntity<LocationsDTO>(savedLocationsDto, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public @ResponseBody ResponseEntity<LocationsDTO> updateLocationDto(@RequestBody LocationDtoPost locationDtoPost, @PathVariable String id) {
        LocationsDTO updatedLocationsDTO = locationService.updateLocationDtoPost(locationDtoPost, Long.valueOf(id));
        return new ResponseEntity<LocationsDTO>(updatedLocationsDTO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            produces = "application/json",
            method = {RequestMethod.DELETE})
    public ResponseEntity<String> deleteLocation(@PathVariable String id) {
        locationService.deleteLocationById(Long.valueOf(id));
        return new ResponseEntity<String>("Deleted the location with id:" + id, HttpStatus.OK);
    }
    @GetMapping(value = "/tripTable", produces = "application/json")
    public ResponseEntity<List<LocationTripDto>> getAllLocationDis() {
        return new ResponseEntity<>(locationService.getAllLocationTrip(), HttpStatus.OK);
    }
}
