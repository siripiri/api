package com.transport.sabi.api.services;

import com.transport.sabi.api.domain.expenses.Expenses;
import com.transport.sabi.api.domain.expenses.ExpensesCategory;
import com.transport.sabi.api.domain.expenses.Fuel;
import com.transport.sabi.api.repository.ExpensesCategoryRepository;
import com.transport.sabi.api.repository.ExpensesRespository;
import com.transport.sabi.api.repository.FuelRepository;
import com.transport.sabi.api.services.exception.BadRequestException;
import com.transport.sabi.api.v1.mapper.ExpensesMapper;
import com.transport.sabi.api.v1.model.expenses.ExpensesCategoryDto;
import com.transport.sabi.api.v1.model.expenses.ExpensesDto;
import com.transport.sabi.api.v1.model.expenses.FuelDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpensesRespository expensesRespository;
    private final ExpensesMapper expensesMapper;
    private final ExpensesCategoryRepository expensesCategoryRepository;
    private final FuelRepository fuelRepository;

    public ExpenseServiceImpl(ExpensesRespository expensesRespository, ExpensesMapper expensesMapper,
                              ExpensesCategoryRepository expensesCategoryRepository,
                              FuelRepository fuelRepository) {
        this.expensesRespository = expensesRespository;
        this.expensesMapper = expensesMapper;
        this.expensesCategoryRepository = expensesCategoryRepository;
        this.fuelRepository = fuelRepository;
    }

    @Override
    public List<ExpensesDto> getAllExpensesWithCategory() {
        return expensesRespository.findAll()
                .stream()
                .map(expensesMapper::expensesToExpensesDto)
                .peek(expensesDto -> expensesDto.setUrl("/api/v1/expenses/" + expensesDto.getId()))
                .collect(Collectors.toList());
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
        ExpensesCategory expensesCategory = expensesCategoryRepository.findByName("Fuel").orElse(null);
        Expenses expenses = expensesMapper.expensesDtoToExpenses(fuelDto.getExpenses());
        expenses.setExpensesCategory(expensesCategory);

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
