package com.finStream.bankmanagementservice.dto;

import com.finStream.bankmanagementservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankRequest {

    private String name;
    private String shortName;
    private String address;
    private String email;
    private String password;
    private String phoneNumber;
}
