package com.transport.sabi.api.domain.repository;

import com.transport.sabi.api.domain.Lorry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LorryRepository extends JpaRepository<Lorry, Long> {
    Optional<Lorry> findByNumberPlate(String numberPlate);
}
