package com.finStream.bankmanagementservice.dto.account;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class AccountTypeDto {


    private String displayName;

    private String useCase;

    public AccountTypeDto( String displayName, String useCase) {

        this.displayName = displayName;
        this.useCase = useCase;
    }
}
