package com.finStream.bankmanagementservice.entity.accountSetting;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("MONEY_MARKET")
public class MoneyMarketAccountsSetting extends AccountSetting {

    private int maxMonthlyTransactions;
}
