package com.finStream.bankmanagementservice.service.account.interfaces;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;

public interface IAccountSettingService {

    AccountSettingDto createAccountSetting(AccountSettingDto accountSettingDto);

    AccountSettingDto updateAccountSetting(AccountSettingDto accountSettingDto);
}
