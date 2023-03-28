package com.transport.sabi.api.bootstrap;

import com.transport.sabi.api.domain.expenses.Expenses;
import com.transport.sabi.api.domain.expenses.ExpensesCategory;
import com.transport.sabi.api.domain.expenses.Fuel;
import com.transport.sabi.api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class LoadData implements CommandLineRunner {

    private final LocationRepository locationRepository;
    private final LorryRepository lorryRepository;
    private final DriverRepository driverRepository;
    private final PersonalInformationRepository personalInformationRepository;
    private final ExpensesRespository expensesRespository;
    private final ExpensesCategoryRepository expensesCategoryRepository;

    private final FuelRepository fuelRepository;

    public LoadData(LocationRepository locationRepository, LorryRepository lorryRepository, DriverRepository driverRepository, PersonalInformationRepository personalInformationRepository, ExpensesRespository expensesRespository, ExpensesCategoryRepository expensesCategoryRepository, FuelRepository fuelRepository) {
        this.locationRepository = locationRepository;
        this.lorryRepository = lorryRepository;
        this.driverRepository = driverRepository;
        this.personalInformationRepository = personalInformationRepository;
        this.expensesRespository = expensesRespository;
        this.expensesCategoryRepository = expensesCategoryRepository;
        this.fuelRepository = fuelRepository;
    }

    private Timestamp getDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = dateFormat.parse(date);
        long time = date1.getTime();
        return new Timestamp(time);
    }

    public List<ExpensesCategory> loadExpensesCategory() {
        ExpensesCategory expensesCategory1 = new ExpensesCategory();
        expensesCategory1.setName("Repair");

        ExpensesCategory expensesCategory2 = new ExpensesCategory();
        expensesCategory2.setName("Fuel");

        return List.of(expensesCategory1, expensesCategory2);
    }

    public List<Expenses> loadExpenses() {
        Expenses expenses1 = new Expenses();
        expenses1.setName("Fuel");
        expenses1.setAmount("1000.00");
        expenses1.setDate("24/05/1999");
        expenses1.setNotes("Hello World");
        ExpensesCategory expensesCategory = expensesCategoryRepository.findByName("Fuel").orElse(null);
        expenses1.setDriver(driverRepository.getReferenceById(1L));
        expenses1.setLorry(lorryRepository.getReferenceById(1L));
        expenses1.setExpensesCategory(expensesCategory);

        Expenses expenses2 = new Expenses();
        expenses2.setName("Fuel");
        expenses2.setAmount("1000.00");
        expenses2.setDate("24/05/1999");
        expenses2.setNotes("Hello World");
        expensesCategory = expensesCategoryRepository.findByName("Repair").orElse(null);
        expenses2.setDriver(driverRepository.getReferenceById(1L));
        expenses2.setLorry(lorryRepository.getReferenceById(1L));
        expenses2.setExpensesCategory(expensesCategory);

        return List.of(expenses1, expenses2);
    }

    public Fuel loadFuelExpenses() {
        Expenses expenses1 = new Expenses();
        expenses1.setName("Fuel");
        expenses1.setAmount("500.00");
        expenses1.setDate("24/05/1999");
        expenses1.setNotes("Hello World");
        ExpensesCategory expensesCategory = expensesCategoryRepository.findByName("Fuel").orElse(null);
        expenses1.setLorry(lorryRepository.getReferenceById(1L));
        expenses1.setExpensesCategory(expensesCategory);

        Expenses saved = expensesRespository.saveAndFlush(expenses1);

        Fuel fuel = new Fuel();
        fuel.setExpenses(saved);
        fuel.setCurrentPrice("100.00");
        fuel.setLiterFilled("5.00");
        fuel.setPaymentMode("CARD");

        return fuel;
    }

    public void loadData() throws ParseException {
        expensesCategoryRepository.saveAllAndFlush(loadExpensesCategory());
        expensesRespository.saveAllAndFlush(loadExpenses());
        fuelRepository.saveAndFlush(loadFuelExpenses());
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }
}
