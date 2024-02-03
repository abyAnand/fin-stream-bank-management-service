package com.finStream.bankmanagementservice.entity.bankSetting;

import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("CHECKING")
public class CheckingAccountsBankSetting extends AccountBankSetting {

    private BigDecimal overdraftLimit;
}
