package com.transport.sabi.api.domain.repository;

import com.transport.sabi.api.domain.expenses.Expenses;
import com.transport.sabi.api.domain.expenses.ExpensesCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface ExpensesRespository extends JpaRepository<Expenses, Long> {
    @Transactional
    @Modifying
    @Query("update Expenses e set e.name = ?1, e.amount = ?2, e.date = ?3, e.notes = ?4, e.expensesCategory = ?5 where e.id = ?6")
    void updateExpenses(@NonNull String name, @NonNull String amount, @NonNull String date, @NonNull String notes,
                        @NonNull ExpensesCategory expensesCategory, @NonNull Long id);
}
