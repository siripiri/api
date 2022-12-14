package com.transport.sabi.api.domain.repository;

import com.transport.sabi.api.domain.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByName(String name);
}
