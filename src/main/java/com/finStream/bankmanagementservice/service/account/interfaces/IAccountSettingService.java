package com.finStream.bankmanagementservice.service.account.interfaces;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;

import java.util.UUID;

public interface IAccountSettingService {

    AccountSettingDto createAccountSetting(AccountSettingDto accountSettingDto);

    AccountSettingDto updateAccountSetting(AccountSettingDto accountSettingDto);

    public AccountSettingDto getAccountSettingById(UUID accountId);
}
