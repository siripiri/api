package com.transport.sabi.api.repository;

import com.transport.sabi.api.domain.expenses.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

public interface FuelRepository extends JpaRepository<Fuel, Long> {
    @Transactional
    @Modifying
    @Query("update Fuel f set f.currentPrice = ?1, f.literFilled = ?2, f.paymentMode = ?3 where id = ?4")
    int updateFuelExpenses(@NonNull String currentPrice, @NonNull String literFilled, @NonNull String paymentMode, @NotNull Long id);
}
