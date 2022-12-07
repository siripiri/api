package com.transport.sabi.api.domain.repository;

import com.transport.sabi.api.domain.CylinderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CylinderDetailRepository extends JpaRepository<CylinderLine, Long> {
}
