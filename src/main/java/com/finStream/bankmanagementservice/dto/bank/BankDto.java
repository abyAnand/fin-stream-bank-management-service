package com.finStream.bankmanagementservice.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankDto {
    private UUID id;
    private String name;
    private String shortName;
    private String email;
    private String address;
    private String phoneNumber;
    private boolean blocked;
}
