package com.transport.sabi.api.controllers.v1;


import com.transport.sabi.api.services.CylinderService;
import com.transport.sabi.api.v1.model.loads.CylinderDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/cylinder")
public class CylinderController {
    private final CylinderService cylinderService;

    public CylinderController(CylinderService cylinderService) {
        this.cylinderService = cylinderService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CylinderDetailsDto>> getAllCylinder() {
        return new ResponseEntity<>(cylinderService.getAllCylinder(), HttpStatus.OK);
    }
}
