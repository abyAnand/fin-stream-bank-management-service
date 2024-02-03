package com.finStream.bankmanagementservice.service.impl;

import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.entity.bankSetting.SavingsAccountsBankSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;

public class SavingsAccountFactory extends AbstractBankSettingFactory {
    public SavingsAccountFactory(AccountBankSettingRepository accountBankSettingRepository) {
        super(accountBankSettingRepository);
    }

    @Override
    public AccountBankSetting createBankSetting(BankSettingDto bankSettingDto) {
        SavingsAccountsBankSetting settings = new SavingsAccountsBankSetting();
        setCommonFields(settings, bankSettingDto);
        settings.setInterestRate(bankSettingDto.getInterestRate());
        return accountBankSettingRepository.save(settings);
    }

    /**
     * @param bankSettingDto
     * @return
     */
    @Override
    public AccountBankSetting updateBankSetting(BankSettingDto bankSettingDto) {
        SavingsAccountsBankSetting settings = accountBankSettingRepository.findSavingsAccountsBankSettingById(bankSettingDto.getId());
        setCommonFields(settings, bankSettingDto);
        settings.setInterestRate(bankSettingDto.getInterestRate());
        return accountBankSettingRepository.save(settings);
    }
}
