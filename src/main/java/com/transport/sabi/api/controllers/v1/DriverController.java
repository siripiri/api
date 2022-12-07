package com.transport.sabi.api.controllers.v1;

import com.transport.sabi.api.services.DriverService;
import com.transport.sabi.api.v1.model.DriverNamesWithLorryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<DriverNamesWithLorryDto>> getDriversNameAndLorry() {
        return new ResponseEntity<>(driverService.getDriversNameAndLorry(), HttpStatus.OK);
    }
}
