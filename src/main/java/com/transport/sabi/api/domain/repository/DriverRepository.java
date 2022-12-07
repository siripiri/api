package com.transport.sabi.api.domain.repository;

import com.transport.sabi.api.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
