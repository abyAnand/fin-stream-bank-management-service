package com.finStream.bankmanagementservice.repository;

import com.finStream.bankmanagementservice.entity.loan.LoanSetting;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanSettingRepository extends JpaRepository<LoanSetting, UUID> {

    List<LoanSetting> findAllByLoanTypeId(UUID loanTypeId);

}
