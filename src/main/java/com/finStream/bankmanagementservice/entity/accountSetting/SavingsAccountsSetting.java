package com.finStream.bankmanagementservice.entity.accountSetting;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("SAVINGS")
public class SavingsAccountsSetting extends AccountSetting {

    private BigDecimal interestRate;
}
