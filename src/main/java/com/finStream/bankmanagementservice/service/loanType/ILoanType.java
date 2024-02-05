package com.finStream.bankmanagementservice.service.loanType;

import com.finStream.bankmanagementservice.dto.loan.LoanSettingDto;
import com.finStream.bankmanagementservice.dto.loan.LoanTypeDto;

import java.util.List;
import java.util.UUID;

public interface ILoanType {

    LoanTypeDto createLoanType(LoanTypeDto loanSettingDto);

    LoanTypeDto getLoanTypeById(UUID id);

    List<LoanTypeDto> getAllLoanTypeByBankId(UUID bankId);

    LoanTypeDto updateLoanType(LoanTypeDto loanSettingDto);

    Boolean deleteLoanType(UUID id);
}
