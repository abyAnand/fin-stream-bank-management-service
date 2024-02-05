package com.finStream.bankmanagementservice.dto.loan;

import com.finStream.bankmanagementservice.enums.LoanCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanSettingDto {

    private UUID id;

    @ToString.Exclude
    private LoanTypeDto loanTypeDto;

    private String name;

    private BigDecimal interestRate;

    private double loanAmount;

    private int loanTermMonths;
}
