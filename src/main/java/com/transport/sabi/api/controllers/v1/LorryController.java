package com.transport.sabi.api.controllers.v1;

import com.transport.sabi.api.controllers.RestResponseEntityExceptionHandler;
import com.transport.sabi.api.services.LorryService;
import com.transport.sabi.api.services.exception.BadRequestException;
import com.transport.sabi.api.v1.model.lorry.LorryDto;
import com.transport.sabi.api.v1.model.lorry.LorryIdPlateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lorry")
@CrossOrigin(maxAge = 3600)
public class LorryController {
    private final LorryService lorryService;

    public LorryController(LorryService lorryService) {
        this.lorryService = lorryService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<LorryDto>> getLorry() {
        return new ResponseEntity<>(lorryService.getAllLorryDto(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getLorryById(@PathVariable String id) {
        try{
            return new ResponseEntity<>(lorryService.getLorryDtoById(Long.valueOf(id)), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new RestResponseEntityExceptionHandler().handleBadRequest(e);
        }
    }

    @GetMapping(value = "/driverName", produces = "application/json")
    public ResponseEntity<List<LorryDto>> getLorryWithDriverNameDto() {
        return new ResponseEntity<>(lorryService.getAllLorryWithDriverNameDto(), HttpStatus.OK);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> saveLorryDto(@RequestBody LorryDto lorryDto) {
        try {
            return new ResponseEntity<>(lorryService.saveLorry(lorryDto), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> updateLorryDto(@RequestBody LorryDto lorryDto) {
        try {
            return new ResponseEntity<>(lorryService.updateLorry(lorryDto), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/unassignDriver", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> unassignDriver(@RequestBody LorryDto lorryDto) {
        try {
            return new ResponseEntity<>(lorryService.unassignDriver(lorryDto), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/assignDriver", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> assignDriver(@RequestBody LorryDto lorryDto) {
        try {
            return new ResponseEntity<>(lorryService.assignDriver(lorryDto), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/numberPlate", produces = "application/json")
    public ResponseEntity<List<LorryIdPlateDto>> getAllLorryNameAndID() {
        return new ResponseEntity<>(lorryService.getAllLorryNameAndId(), HttpStatus.OK);
    }
}
