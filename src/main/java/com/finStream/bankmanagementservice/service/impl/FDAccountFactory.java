package com.finStream.bankmanagementservice.service.impl;

import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.entity.bankSetting.FDAccountsBankSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;

public class FDAccountFactory extends AbstractBankSettingFactory {
    public FDAccountFactory(AccountBankSettingRepository accountBankSettingRepository) {
        super(accountBankSettingRepository);
    }

    @Override
    public AccountBankSetting createBankSetting(BankSettingDto bankSettingDto) {
        FDAccountsBankSetting settings = new FDAccountsBankSetting();
        setCommonFields(settings, bankSettingDto);
        settings.setCdTerm(bankSettingDto.getCdTerm());
        settings.setInterestRate(bankSettingDto.getInterestRate());
        return accountBankSettingRepository.save(settings);
    }

    /**
     * @param bankSettingDto
     * @return
     */
    @Override
    public AccountBankSetting updateBankSetting(BankSettingDto bankSettingDto) {
        FDAccountsBankSetting settings = accountBankSettingRepository.findFDAccountsBankSettingById(bankSettingDto.getId());
        setCommonFields(settings, bankSettingDto);
        settings.setCdTerm(bankSettingDto.getCdTerm());
        settings.setInterestRate(bankSettingDto.getInterestRate());
        return accountBankSettingRepository.save(settings);
    }
}
