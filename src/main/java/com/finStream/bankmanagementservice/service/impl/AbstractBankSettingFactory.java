package com.finStream.bankmanagementservice.service.impl;

import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
import com.finStream.bankmanagementservice.service.interfaces.BankSettingFactory;

public abstract class AbstractBankSettingFactory implements BankSettingFactory {
    protected final AccountBankSettingRepository accountBankSettingRepository;

    public AbstractBankSettingFactory(AccountBankSettingRepository accountBankSettingRepository) {
        this.accountBankSettingRepository = accountBankSettingRepository;
    }

    protected void setCommonFields(AccountBankSetting settings, BankSettingDto bankSettingDto) {
        settings.setBankId(bankSettingDto.getBankId());
        settings.setAccountName(bankSettingDto.getAccountName());
    }
}
