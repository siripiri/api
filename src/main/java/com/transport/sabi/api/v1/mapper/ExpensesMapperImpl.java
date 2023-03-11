package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.domain.expenses.Expenses;
import com.transport.sabi.api.domain.expenses.ExpensesCategory;
import com.transport.sabi.api.domain.expenses.Fuel;
import com.transport.sabi.api.v1.model.driverDto.DriverNameDto;
import com.transport.sabi.api.v1.model.expenses.ExpensesCategoryDto;
import com.transport.sabi.api.v1.model.expenses.ExpensesDto;
import com.transport.sabi.api.v1.model.expenses.FuelDto;
import com.transport.sabi.api.v1.model.lorry.LorryIdPlateDto;
import org.springframework.stereotype.Component;

@Component
public class ExpensesMapperImpl implements ExpensesMapper {

    @Override
    public ExpensesDto expensesToExpensesDto(Expenses expenses) {
        if(expenses == null) return null;

        ExpensesDto expensesDto = new ExpensesDto();
        expensesDto.setId(expenses.getId());
        expensesDto.setName(expenses.getName());
        expensesDto.setAmount(expenses.getAmount());
        expensesDto.setDate(expenses.getDate());
        expensesDto.setNotes(expenses.getNotes());
        expensesDto.setExpensesCategory(expenses.getExpensesCategory().getName());

        if(expenses.getDriver() != null){
            DriverNameDto driverNameDto = new DriverNameDto();
            driverNameDto.setId(expenses.getDriver().getId());
            driverNameDto.setDriverName(expenses.getDriver().getName());
            expensesDto.setDriver(driverNameDto);
        }

        if(expenses.getLorry() != null){
            LorryIdPlateDto lorry = new LorryIdPlateDto();
            lorry.setId(expenses.getLorry().getId());
            lorry.setNumberPlate(expenses.getLorry().getNumberPlate());
            expensesDto.setLorry(lorry);
        }

        return expensesDto;
    }

    @Override
    public ExpensesCategoryDto expensesCategoryToExpensesCategoryDto(ExpensesCategory expensesCategory) {
        if(expensesCategory == null) return null;

        ExpensesCategoryDto expensesCategoryDto = new ExpensesCategoryDto();
        expensesCategoryDto.setId(expensesCategory.getId());
        expensesCategoryDto.setExpenseCategory(expensesCategory.getName());
        return expensesCategoryDto;
    }

    @Override
    public Expenses expensesDtoToExpenses(ExpensesDto expensesDto) {
        if(expensesDto == null) return null;

        Expenses expenses = new Expenses();
        expenses.setName(expensesDto.getName());
        expenses.setDate(expensesDto.getDate());
        expenses.setAmount(expensesDto.getAmount());
        expenses.setNotes(expensesDto.getNotes());
        if(expensesDto.getId() != null) {
            expenses.setId(expensesDto.getId());
        }

        return expenses;
    }

    @Override
    public FuelDto fuelToFuelDto(Fuel fuel) {
        if(fuel == null) return null;

        FuelDto fuelDto = new FuelDto();
        fuelDto.setId(fuel.getId());
        fuelDto.setCurrentPrice(fuel.getCurrentPrice());
        fuelDto.setPaymentMode(fuel.getPaymentMode());
        fuelDto.setLiterFilled(fuel.getLiterFilled());
        fuelDto.setExpenses(expensesToExpensesDto(fuel.getExpenses()));
        fuelDto.getExpenses().setUrl("/api/v1/expenses/" + fuelDto.getExpenses().getId());
        return fuelDto;
    }

    @Override
    public Fuel fuelDtoToFuel(FuelDto fuelDto) {
        if(fuelDto == null) return null;

        Fuel fuel = new Fuel();
        fuel.setExpenses(expensesDtoToExpenses(fuelDto.getExpenses()));
        fuel.setPaymentMode(fuelDto.getPaymentMode());
        fuel.setLiterFilled(fuelDto.getLiterFilled());
        fuel.setCurrentPrice(fuelDto.getCurrentPrice());

        return fuel;
    }
}
