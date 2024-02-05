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
@DiscriminatorValue("CHECKING")
public class CheckingAccountsSetting extends AccountSetting {

    private BigDecimal overdraftLimit;
}
