package com.transport.sabi.api.repository;

import com.transport.sabi.api.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByDistributorName(String distributorName);
}
