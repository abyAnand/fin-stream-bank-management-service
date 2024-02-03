package com.finStream.bankmanagementservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTypeInfoDto {

    private String displayName;
    private String shortName;
    private int numberOfAccounts;
}
