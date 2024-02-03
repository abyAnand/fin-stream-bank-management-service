package com.finStream.bankmanagementservice.service.interfaces;

import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;

public interface BankSettingFactory {
    AccountBankSetting createBankSetting(BankSettingDto bankSettingDto);

    AccountBankSetting updateBankSetting(BankSettingDto bankSettingDto);
}
