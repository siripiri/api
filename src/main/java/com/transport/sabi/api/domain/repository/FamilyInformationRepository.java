package com.transport.sabi.api.domain.repository;

import com.transport.sabi.api.domain.driver.FamilyInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Set;

public interface FamilyInformationRepository extends JpaRepository<FamilyInformation, Long> {
    @Transactional
    @Query("select F from FamilyInformation F where driver_id = ?1")
    Set<FamilyInformation> getFamilyInformationByDriverId(@NotNull Long driverId);
}
