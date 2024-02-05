package com.finStream.bankmanagementservice.repository;

import com.finStream.bankmanagementservice.entity.bank.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, UUID> {
    Optional<BankEntity> findByName(String name);

    Optional<BankEntity> findByShortName(String shortName);
}
