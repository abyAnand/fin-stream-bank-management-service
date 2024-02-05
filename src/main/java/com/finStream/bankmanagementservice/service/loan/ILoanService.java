package com.finStream.bankmanagementservice.service.loan;

import com.finStream.bankmanagementservice.dto.loan.LoanSettingDto;

import java.util.List;
import java.util.UUID;

public interface ILoanService {

    LoanSettingDto createLoanSetting(LoanSettingDto loanSettingDto);

    LoanSettingDto getLoanSettingById(UUID id);

    List<LoanSettingDto> getAllLoanSettingsByLoanTypeId(UUID bankId);

    LoanSettingDto updateLoanSetting(LoanSettingDto loanSettingDto);

    Boolean deleteLoanSetting(UUID id);
}
