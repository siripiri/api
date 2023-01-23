package com.transport.sabi.api.controllers.v1;

import com.transport.sabi.api.services.ExpenseService;
import com.transport.sabi.api.v1.model.expenses.FuelDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/fuel")
@CrossOrigin(maxAge = 3600)
public class FuelController {
    private final ExpenseService expenseService;

    public FuelController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<FuelDto>> getLocations() {
        return new ResponseEntity<>(expenseService.getAllFuelDto(), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<FuelDto> putFuelExpenses(@RequestBody FuelDto fuelDto) {
        return new ResponseEntity<>(expenseService.saveFuelExpenses(fuelDto), HttpStatus.OK);
    }

    @PatchMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<FuelDto> patchFuelExpenses(@RequestBody FuelDto fuelDto) {
        return new ResponseEntity<>(expenseService.updateFuelExpenses(fuelDto), HttpStatus.OK);
    }
}
