package com.transport.sabi.api.repository;

import com.transport.sabi.api.domain.driver.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Set;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
    @Transactional
    @Query("Select E from EmergencyContact E where driver_id = ?1")
    Set<EmergencyContact> getEmergencyContactsByDriverId(@NotNull Long driverId);
}
