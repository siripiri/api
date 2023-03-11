package com.transport.sabi.api.services;

import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.domain.expenses.Expenses;
import com.transport.sabi.api.domain.expenses.ExpensesCategory;
import com.transport.sabi.api.domain.expenses.Fuel;
import com.transport.sabi.api.repository.*;
import com.transport.sabi.api.services.exception.BadRequestException;
import com.transport.sabi.api.services.exception.ResourceNotFoundException;
import com.transport.sabi.api.v1.mapper.ExpensesMapper;
import com.transport.sabi.api.v1.model.expenses.ExpensesCategoryDto;
import com.transport.sabi.api.v1.model.expenses.ExpensesDto;
import com.transport.sabi.api.v1.model.expenses.FuelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    Logger log = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    private final ExpensesRespository expensesRespository;
    private final ExpensesMapper expensesMapper;
    private final ExpensesCategoryRepository expensesCategoryRepository;
    private final FuelRepository fuelRepository;
    private final DriverRepository driverRepository;
    private final LorryRepository lorryRepository;

    public ExpenseServiceImpl(ExpensesRespository expensesRespository, ExpensesMapper expensesMapper, ExpensesCategoryRepository expensesCategoryRepository, FuelRepository fuelRepository, DriverRepository driverRepository, LorryRepository lorryRepository) {
        this.expensesRespository = expensesRespository;
        this.expensesMapper = expensesMapper;
        this.expensesCategoryRepository = expensesCategoryRepository;
        this.fuelRepository = fuelRepository;
        this.driverRepository = driverRepository;
        this.lorryRepository = lorryRepository;
    }

    @Override
    public List<ExpensesDto> getAllExpensesWithCategory() {
        log.info("Fetching expenses list from DB");
        List<ExpensesDto> expensesDtoList =  expensesRespository.findAll()
                                                .stream()
                                                .map(expensesMapper::expensesToExpensesDto)
                                                .peek(expensesDto -> expensesDto.setUrl("/api/v1/expenses/" + expensesDto.getId()))
                                                .toList();
        if(expensesDtoList.isEmpty()) {
            log.info("No record found in DB");
            throw new ResourceNotFoundException("Resource Not Found");
        }
        log.info(expensesDtoList.toString());
        return expensesDtoList;
    }

    @Override
    public List<ExpensesCategoryDto> getAllExpensesCategory() {
        return expensesCategoryRepository.findAll()
                .stream()
                .map(expensesMapper::expensesCategoryToExpensesCategoryDto)
                .peek(expensesCategoryDto -> expensesCategoryDto.setUrl("/api/v1/expensesCategory/" + expensesCategoryDto.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public ExpensesDto saveExpensesDto(ExpensesDto expensesDto) {
        ExpensesCategory expensesCategory = expensesCategoryRepository.findByName(expensesDto.getExpensesCategory()).orElse(null);
        Expenses expenses = expensesMapper.expensesDtoToExpenses(expensesDto);
        expenses.setExpensesCategory(expensesCategory);
        if(expensesDto.getDriver() != null){
            Driver driver = driverRepository.findById(expensesDto.getDriver().getId()).orElseThrow(BadRequestException::new);
            expenses.setDriver(driver);
        }
        if(expensesDto.getLorry() != null){
            Lorry lorry = lorryRepository.findById(expensesDto.getLorry().getId()).orElseThrow(BadRequestException::new);
            expenses.setLorry(lorry);
        }
        return expensesMapper.expensesToExpensesDto(expensesRespository.saveAndFlush(expenses));
    }

    @Override
    public ExpensesDto updateExpensesDto(ExpensesDto expensesDto) {
        ExpensesCategory expensesCategory = expensesCategoryRepository.findByName(expensesDto.getExpensesCategory()).orElse(null);
        if (expensesCategory == null) {
            throw new BadRequestException("expenses category is null");
        }
        expensesRespository.updateExpenses(expensesDto.getName(), expensesDto.getAmount(), expensesDto.getDate(),
                expensesDto.getNotes(), expensesCategory, expensesDto.getId());

        return expensesMapper.expensesToExpensesDto(expensesRespository.getReferenceById(expensesDto.getId()));
    }

    @Override
    public List<FuelDto> getAllFuelDto() {
        return fuelRepository.findAll()
                .stream()
                .map(expensesMapper::fuelToFuelDto)
                .peek(fuelDto -> fuelDto.setUrl("/api/v1/fuelExpenses/" + fuelDto.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public ExpensesDto getExpensesById(Long id) {
        return expensesRespository.findById(id)
                .map(expensesMapper::expensesToExpensesDto)
                .map(expensesDto -> {
                    expensesDto.setUrl("/api/v1/expenses/" + expensesDto.getId());
                    return expensesDto;
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public FuelDto saveFuelExpenses(FuelDto fuelDto) {
        ExpensesCategory expensesCategory = expensesCategoryRepository.findByName("Fuel").orElseThrow(BadRequestException::new);
        Lorry lorry = lorryRepository.findById(fuelDto.getExpenses().getLorry().getId()).orElseThrow(BadRequestException::new);
        Expenses expenses = expensesMapper.expensesDtoToExpenses(fuelDto.getExpenses());
        expenses.setExpensesCategory(expensesCategory);
        expenses.setLorry(lorry);

        Expenses savedExpenses = expensesRespository.saveAndFlush(expenses);

        Fuel fuel = expensesMapper.fuelDtoToFuel(fuelDto);
        fuel.setExpenses(savedExpenses);

        return expensesMapper.fuelToFuelDto(fuelRepository.save(fuel));
    }

    @Override
    public FuelDto updateFuelExpenses(FuelDto fuelDto) {
        fuelDto.getExpenses().setExpensesCategory("Fuel");
        updateExpensesDto(fuelDto.getExpenses());
        fuelRepository.updateFuelExpenses(fuelDto.getCurrentPrice(), fuelDto.getLiterFilled(), fuelDto.getPaymentMode(), fuelDto.getId());
        return expensesMapper.fuelToFuelDto(fuelRepository.getReferenceById(fuelDto.getId()));
    }
}
