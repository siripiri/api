package com.transport.sabi.api.domain.repository;

import com.transport.sabi.api.domain.driver.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {
    @Transactional
    @Query("Select P from PersonalInformation P where driver_id = ?1")
    Optional<PersonalInformation> getPersonalInformationByDriverId(@NotNull Long driverId);
}
