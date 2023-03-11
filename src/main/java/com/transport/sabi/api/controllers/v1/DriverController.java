package com.transport.sabi.api.controllers.v1;

import com.transport.sabi.api.controllers.RestResponseEntityExceptionHandler;
import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.services.DriverService;
import com.transport.sabi.api.v1.model.driverDto.DriverDto;
import com.transport.sabi.api.v1.model.driverDto.DriverFormDto;
import com.transport.sabi.api.v1.model.driverDto.DriverNameDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/driver")
@CrossOrigin(maxAge = 3600)
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping(value = "/names", produces = "application/json")
    public ResponseEntity<List<DriverDto>> getDriversNameAndLorry() {
        return new ResponseEntity<>(driverService.getDriversNameAndLorry(), HttpStatus.OK);
    }

    @GetMapping(value = "/lorry", produces = "application/json")
    public ResponseEntity<List<DriverDto>> getDriversWithLorry() {
        return new ResponseEntity<>(driverService.getDriversWithLorry(), HttpStatus.OK);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<DriverFormDto> saveDriverDto(@RequestBody DriverFormDto driverFormDto) {
        return new ResponseEntity<>(driverService.saveDriverFormDto(driverFormDto), HttpStatus.OK);
    }

    @PatchMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<DriverDto> updateDriverDto(@RequestBody DriverDto driverDto) {
        return new ResponseEntity<>(driverService.update(driverDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getDriverById(@PathVariable String id) {
        try{
            return new ResponseEntity<>(driverService.getDriverFormDtoById(Long.valueOf(id)), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new RestResponseEntityExceptionHandler().handleBadRequest(e);
        }
    }

    @GetMapping(value = "/idAndName", produces = "application/json")
    public ResponseEntity<List<DriverNameDto>> getDriverNameAndId() {
        return new ResponseEntity<>(driverService.getAllDriverName(), HttpStatus.OK);
    }
}
