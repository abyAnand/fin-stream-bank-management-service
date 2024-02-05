package com.finStream.bankmanagementservice.dto.account;

import com.finStream.bankmanagementservice.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AccountSettingDto {

    private UUID id;

    private UUID bankId;

    private AccountType accountType;

    private String accountName;

    private BigDecimal overdraftLimit;

    private int cdTerm;

    private int accountHoldersLimit;

    private int maxMonthlyTransactions;

    private BigDecimal interestRate;


}
