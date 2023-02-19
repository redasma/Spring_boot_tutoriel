package com.redouaneCode.spring.security.client.repository;

import com.redouaneCode.spring.security.client.entity.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {
}
