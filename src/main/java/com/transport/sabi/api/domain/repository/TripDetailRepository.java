package com.transport.sabi.api.domain.repository;

import com.transport.sabi.api.domain.TripDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripDetailRepository extends JpaRepository<TripDetail, Long> {
}
