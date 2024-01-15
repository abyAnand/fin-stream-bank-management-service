package com.finStream.bankmanagementservice.service;


import com.finStream.bankmanagementservice.dto.AccountTypeDto;
import com.finStream.bankmanagementservice.enums.AccountType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountTypeService {

    public List<AccountTypeDto> getAccountTypes() {
        return Arrays.stream(AccountType.values())
                .map(accountType -> new AccountTypeDto(
                        accountType.getDisplayName(),
                        accountType.getUseCase()))
                .collect(Collectors.toList());
    }
}
