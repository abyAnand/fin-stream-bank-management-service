package com.finStream.bankmanagementservice.service.account.impl;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
import com.finStream.bankmanagementservice.service.account.interfaces.AccountSettingFactory;

public abstract class AbstractAccountSettingFactory implements AccountSettingFactory {
    protected final AccountBankSettingRepository accountBankSettingRepository;

    public AbstractAccountSettingFactory(AccountBankSettingRepository accountBankSettingRepository) {
        this.accountBankSettingRepository = accountBankSettingRepository;
    }

    protected void setCommonFields(AccountSetting settings, AccountSettingDto accountSettingDto) {
        settings.setBankId(accountSettingDto.getBankId());

        settings.setAccountName(accountSettingDto.getAccountName());
    }
}
