package com.finStream.bankmanagementservice.dto;

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
public class BankSettingDto {

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
