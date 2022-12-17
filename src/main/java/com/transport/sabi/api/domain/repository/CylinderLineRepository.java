package com.transport.sabi.api.domain.repository;

import com.transport.sabi.api.domain.CylinderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CylinderLineRepository extends JpaRepository<CylinderLine, Long> {
}
