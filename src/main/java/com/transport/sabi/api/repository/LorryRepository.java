package com.transport.sabi.api.repository;

import com.transport.sabi.api.domain.Lorry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LorryRepository extends JpaRepository<Lorry, Long> {
    @Transactional
    @Modifying
    @Query("update Lorry l set l.numberPlate = ?1, l.modelNumber = ?2, l.manufacturer = ?3, l.type = ?4 where l.id = ?5")
    void updateLorryById(@NonNull String numberPlate, @NonNull String modelNumber, @NonNull String manufacturer,
                     @NonNull String type, @NonNull Long id);
    Optional<Lorry> findByNumberPlate(String numberPlate);
}
