package com.finStream.bankmanagementservice.dto.loan;

import com.finStream.bankmanagementservice.entity.Image;
import com.finStream.bankmanagementservice.entity.loan.LoanSetting;
import com.finStream.bankmanagementservice.enums.LoanCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanTypeDto {

    private UUID id;

    private UUID bankId;

    private String name;

    private Image image;

    @Enumerated(EnumType.STRING)
    private LoanCategory loanCategory;

    @ToString.Exclude
    List<LoanSettingDto> loanSettingList;
}
