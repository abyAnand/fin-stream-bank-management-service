package com.finStream.bankmanagementservice.service.account.interfaces;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;

public interface AccountSettingFactory {
    AccountSetting createBankSetting(AccountSettingDto accountSettingDto);

    AccountSetting updateBankSetting(AccountSettingDto accountSettingDto);
}
