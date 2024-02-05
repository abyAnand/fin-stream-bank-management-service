package com.finStream.bankmanagementservice.service.account.impl;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.accountSetting.JointAccountsSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;

public class JointAccountFactory extends AbstractAccountSettingFactory {
    public JointAccountFactory(AccountBankSettingRepository accountBankSettingRepository) {
        super(accountBankSettingRepository);
    }

    @Override
    public AccountSetting createBankSetting(AccountSettingDto accountSettingDto) {
        JointAccountsSetting settings = new JointAccountsSetting();
        setCommonFields(settings, accountSettingDto);
        settings.setAccountHoldersLimit(accountSettingDto.getAccountHoldersLimit());
        return accountBankSettingRepository.save(settings);
    }

    /**
     * @param accountSettingDto
     * @return
     */
    @Override
    public AccountSetting updateBankSetting(AccountSettingDto accountSettingDto) {
        JointAccountsSetting settings = accountBankSettingRepository.findJointAccountsBankSettingById(accountSettingDto.getId());
        setCommonFields(settings, accountSettingDto);
        settings.setAccountHoldersLimit(accountSettingDto.getAccountHoldersLimit());
        return accountBankSettingRepository.save(settings);
    }
}
