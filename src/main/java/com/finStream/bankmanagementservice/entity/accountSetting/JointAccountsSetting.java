package com.finStream.bankmanagementservice.entity.accountSetting;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("JOINT")
public class JointAccountsSetting extends AccountSetting {

    private int accountHoldersLimit;
}
