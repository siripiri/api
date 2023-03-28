package com.transport.sabi.api.controllers.v1;

import com.transport.sabi.api.services.ExpenseService;
import com.transport.sabi.api.v1.model.expenses.ExpensesCategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/expensesCategory")
public class ExpensesCategoryController {
    private final ExpenseService expenseService;

    public ExpensesCategoryController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ExpensesCategoryDto>> getLocations() {
        return new ResponseEntity<>(expenseService.getAllExpensesCategory(), HttpStatus.OK);
    }
}
