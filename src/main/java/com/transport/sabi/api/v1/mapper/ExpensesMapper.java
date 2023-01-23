package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.expenses.Expenses;
import com.transport.sabi.api.domain.expenses.ExpensesCategory;
import com.transport.sabi.api.domain.expenses.Fuel;
import com.transport.sabi.api.v1.model.expenses.ExpensesCategoryDto;
import com.transport.sabi.api.v1.model.expenses.ExpensesDto;
import com.transport.sabi.api.v1.model.expenses.FuelDto;

public interface ExpensesMapper {
    ExpensesDto expensesToExpensesDto(Expenses expenses);
    ExpensesCategoryDto expensesCategoryToExpensesCategoryDto(ExpensesCategory expensesCategory);
    Expenses expensesDtoToExpenses(ExpensesDto expensesDto);
    FuelDto fuelToFuelDto(Fuel fuel);
    Fuel fuelDtoToFuel(FuelDto fuelDto);
}
