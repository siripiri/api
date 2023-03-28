package com.transport.sabi.api.repository;

import com.transport.sabi.api.domain.driver.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {
}
