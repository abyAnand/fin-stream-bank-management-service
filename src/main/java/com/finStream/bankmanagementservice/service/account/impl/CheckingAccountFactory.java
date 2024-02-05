package com.finStream.bankmanagementservice.service.account.impl;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.accountSetting.CheckingAccountsSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;

public class CheckingAccountFactory extends AbstractAccountSettingFactory {
    public CheckingAccountFactory(AccountBankSettingRepository accountBankSettingRepository) {
        super(accountBankSettingRepository);
    }

    @Override
    public AccountSetting createBankSetting(AccountSettingDto accountSettingDto) {
        CheckingAccountsSetting settings = new CheckingAccountsSetting();
        setCommonFields(settings, accountSettingDto);
        settings.setOverdraftLimit(accountSettingDto.getOverdraftLimit());
        return accountBankSettingRepository.save(settings);
    }

    /**
     * @param accountSettingDto
     * @return
     */
    @Override
    public AccountSetting updateBankSetting(AccountSettingDto accountSettingDto) {
        CheckingAccountsSetting settings = accountBankSettingRepository.findCheckingAccountsBankSettingById(accountSettingDto.getId());
        setCommonFields(settings, accountSettingDto);
        settings.setOverdraftLimit(accountSettingDto.getOverdraftLimit());
        return accountBankSettingRepository.save(settings);
    }
}
