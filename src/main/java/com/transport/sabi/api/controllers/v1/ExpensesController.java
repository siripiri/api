package com.transport.sabi.api.controllers.v1;

import com.transport.sabi.api.controllers.RestResponseEntityExceptionHandler;
import com.transport.sabi.api.services.ExpenseService;
import com.transport.sabi.api.v1.model.expenses.ExpensesDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/expenses")
public class ExpensesController {
    private final ExpenseService expenseService;

    public ExpensesController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ExpensesDto>> getAllExpenses() {
        return new ResponseEntity<>(expenseService.getAllExpensesWithCategory(), HttpStatus.OK);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ExpensesDto> saveExpensesDto(@RequestBody ExpensesDto expensesDto) {
        return new ResponseEntity<>(expenseService.saveExpensesDto(expensesDto), HttpStatus.OK);
    }

    @PatchMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ExpensesDto> updateExpensesDto(@RequestBody ExpensesDto expensesDto) {
        return new ResponseEntity<>(expenseService.updateExpensesDto(expensesDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> getExpensesById(@PathVariable String id) {
        try{
            return new ResponseEntity<>(expenseService.getExpensesById(Long.valueOf(id)), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new RestResponseEntityExceptionHandler().handleBadRequest(e);
        }
    }
}
