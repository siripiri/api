package com.transport.sabi.api.repository;

import com.transport.sabi.api.domain.driver.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
}
