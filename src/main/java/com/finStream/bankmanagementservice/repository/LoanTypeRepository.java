package com.finStream.bankmanagementservice.repository;


import com.finStream.bankmanagementservice.entity.loan.LoanSetting;
import com.finStream.bankmanagementservice.entity.loan.LoanType;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, UUID> {
    List<LoanType> findAllByBankId(UUID bankId);
}
