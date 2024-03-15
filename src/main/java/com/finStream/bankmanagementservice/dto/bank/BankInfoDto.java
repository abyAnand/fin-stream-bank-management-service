package com.finStream.bankmanagementservice.dto.bank;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.dto.loan.LoanSettingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankInfoDto {

    private UUID id;
    private String name;
    private String shortName;
    private String email;
    private boolean verified;
    private List<AccountSettingDto> accountSettingDtoList;
    private List<LoanSettingDto> loanSettingDtoList;
}
