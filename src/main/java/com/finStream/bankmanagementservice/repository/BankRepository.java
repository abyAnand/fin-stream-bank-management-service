package com.finStream.bankmanagementservice.repository;

import com.finStream.bankmanagementservice.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {
    Optional<Bank> findByName(String name);

    Optional<Bank> findByShortName(String shortName);
}
