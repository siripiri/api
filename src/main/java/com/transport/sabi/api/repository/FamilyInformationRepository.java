package com.transport.sabi.api.repository;

import com.transport.sabi.api.domain.driver.FamilyInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyInformationRepository extends JpaRepository<FamilyInformation, Long> {
}
