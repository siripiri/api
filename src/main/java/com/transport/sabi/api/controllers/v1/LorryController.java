package com.transport.sabi.api.controllers.v1;

import com.transport.sabi.api.controllers.RestResponseEntityExceptionHandler;
import com.transport.sabi.api.services.LorryService;
import com.transport.sabi.api.v1.model.LorryDto;
import com.transport.sabi.api.v1.model.LorryWithDriverNameDto;
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
        return new ResponseEntity<List<LorryDto>>(lorryService.getAllLorryDto(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getLorryById(@PathVariable String id) {
        try{
            return new ResponseEntity<Object>(lorryService.getLorryDtoById(Long.valueOf(id)), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new RestResponseEntityExceptionHandler().handleBadRequest(e);
        }
    }

    @GetMapping(value = "/driverName", produces = "application/json")
    public ResponseEntity<List<LorryWithDriverNameDto>> getLorryWithDriverNameDto() {
        return new ResponseEntity<>(lorryService.getAllLorryWithDriverNameDto(), HttpStatus.OK);
    }
}
