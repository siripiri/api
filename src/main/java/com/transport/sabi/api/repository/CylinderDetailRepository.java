package com.transport.sabi.api.repository;

import com.transport.sabi.api.domain.load.CylinderDetail;
import com.transport.sabi.api.domain.load.CylinderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CylinderDetailRepository extends JpaRepository<CylinderDetail, Long> {
}
