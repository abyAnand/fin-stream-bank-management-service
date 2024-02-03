package com.finStream.bankmanagementservice.service.impl;

import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.entity.bankSetting.JointAccountsBankSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;

public class JointAccountFactory extends AbstractBankSettingFactory {
    public JointAccountFactory(AccountBankSettingRepository accountBankSettingRepository) {
        super(accountBankSettingRepository);
    }

    @Override
    public AccountBankSetting createBankSetting(BankSettingDto bankSettingDto) {
        JointAccountsBankSetting settings = new JointAccountsBankSetting();
        setCommonFields(settings, bankSettingDto);
        settings.setAccountHoldersLimit(bankSettingDto.getAccountHoldersLimit());
        return accountBankSettingRepository.save(settings);
    }

    /**
     * @param bankSettingDto
     * @return
     */
    @Override
    public AccountBankSetting updateBankSetting(BankSettingDto bankSettingDto) {
        JointAccountsBankSetting settings = accountBankSettingRepository.findJointAccountsBankSettingById(bankSettingDto.getId());
        setCommonFields(settings, bankSettingDto);
        settings.setAccountHoldersLimit(bankSettingDto.getAccountHoldersLimit());
        return accountBankSettingRepository.save(settings);
    }
}
