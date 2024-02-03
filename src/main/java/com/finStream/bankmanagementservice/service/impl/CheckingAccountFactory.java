package com.finStream.bankmanagementservice.service.impl;

import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.entity.bankSetting.CheckingAccountsBankSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;

public class CheckingAccountFactory extends AbstractBankSettingFactory {
    public CheckingAccountFactory(AccountBankSettingRepository accountBankSettingRepository) {
        super(accountBankSettingRepository);
    }

    @Override
    public AccountBankSetting createBankSetting(BankSettingDto bankSettingDto) {
        CheckingAccountsBankSetting settings = new CheckingAccountsBankSetting();
        setCommonFields(settings, bankSettingDto);
        settings.setOverdraftLimit(bankSettingDto.getOverdraftLimit());
        return accountBankSettingRepository.save(settings);
    }

    /**
     * @param bankSettingDto
     * @return
     */
    @Override
    public AccountBankSetting updateBankSetting(BankSettingDto bankSettingDto) {
        CheckingAccountsBankSetting settings = accountBankSettingRepository.findCheckingAccountsBankSettingById(bankSettingDto.getId());
        setCommonFields(settings, bankSettingDto);
        settings.setOverdraftLimit(bankSettingDto.getOverdraftLimit());
        return accountBankSettingRepository.save(settings);
    }
}
