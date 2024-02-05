package com.finStream.bankmanagementservice.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountTypeListRequestDto {

    private UUID bankId;
    private String accountType;
}
