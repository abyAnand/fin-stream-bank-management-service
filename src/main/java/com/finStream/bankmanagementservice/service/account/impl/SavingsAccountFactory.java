package com.finStream.bankmanagementservice.service.account.impl;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.accountSetting.SavingsAccountsSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;

public class SavingsAccountFactory extends AbstractAccountSettingFactory {
    public SavingsAccountFactory(AccountBankSettingRepository accountBankSettingRepository) {
        super(accountBankSettingRepository);
    }

    @Override
    public AccountSetting createBankSetting(AccountSettingDto accountSettingDto) {
        SavingsAccountsSetting settings = new SavingsAccountsSetting();
        setCommonFields(settings, accountSettingDto);
        settings.setInterestRate(accountSettingDto.getInterestRate());
        return accountBankSettingRepository.save(settings);
    }

    /**
     * @param accountSettingDto
     * @return
     */
    @Override
    public AccountSetting updateBankSetting(AccountSettingDto accountSettingDto) {
        SavingsAccountsSetting settings = accountBankSettingRepository.findSavingsAccountsBankSettingById(accountSettingDto.getId());
        setCommonFields(settings, accountSettingDto);
        settings.setInterestRate(accountSettingDto.getInterestRate());
        return accountBankSettingRepository.save(settings);
    }
}
