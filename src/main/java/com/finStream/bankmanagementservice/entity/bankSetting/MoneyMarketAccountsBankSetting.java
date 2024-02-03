package com.finStream.bankmanagementservice.entity.bankSetting;

import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("MONEY_MARKET")
public class MoneyMarketAccountsBankSetting extends AccountBankSetting {

    private int maxMonthlyTransactions;
}
