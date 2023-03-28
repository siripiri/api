package com.transport.sabi.api.repository;

import com.transport.sabi.api.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    Optional<UserDetail> findByEmail(String email);
}
