package com.transport.sabi.api.services;

import com.transport.sabi.api.v1.model.expenses.ExpensesCategoryDto;
import com.transport.sabi.api.v1.model.expenses.ExpensesDto;
import com.transport.sabi.api.v1.model.expenses.FuelDto;

import java.util.List;

public interface ExpenseService {
    List<ExpensesDto> getAllExpensesWithCategory();
    List<ExpensesCategoryDto> getAllExpensesCategory();
    ExpensesDto saveExpensesDto(ExpensesDto expensesDto);
    ExpensesDto updateExpensesDto(ExpensesDto expensesDto);
    List<FuelDto> getAllFuelDto();
    ExpensesDto getExpensesById(Long id);
    FuelDto saveFuelExpenses(FuelDto fuelDto);
    FuelDto updateFuelExpenses(FuelDto fuelDto);
}
