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
@DiscriminatorValue("FD")
public class FDAccountsSetting extends AccountSetting {

    private BigDecimal interestRate;

    private int cdTerm;
}
