package com.transport.sabi.api.repository;

import com.transport.sabi.api.domain.expenses.ExpensesCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpensesCategoryRepository extends JpaRepository<ExpensesCategory, Long> {
    Optional<ExpensesCategory> findByName(String name);
}
