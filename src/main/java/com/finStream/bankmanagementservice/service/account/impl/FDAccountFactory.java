package com.finStream.bankmanagementservice.service.account.impl;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.accountSetting.FDAccountsSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;

public class FDAccountFactory extends AbstractAccountSettingFactory {
    public FDAccountFactory(AccountBankSettingRepository accountBankSettingRepository) {
        super(accountBankSettingRepository);
    }

    @Override
    public AccountSetting createBankSetting(AccountSettingDto accountSettingDto) {
        FDAccountsSetting settings = new FDAccountsSetting();
        setCommonFields(settings, accountSettingDto);
        settings.setCdTerm(accountSettingDto.getCdTerm());
        settings.setInterestRate(accountSettingDto.getInterestRate());
        return accountBankSettingRepository.save(settings);
    }

    /**
     * @param accountSettingDto
     * @return
     */
    @Override
    public AccountSetting updateBankSetting(AccountSettingDto accountSettingDto) {
        FDAccountsSetting settings = accountBankSettingRepository.findFDAccountsBankSettingById(accountSettingDto.getId());
        setCommonFields(settings, accountSettingDto);
        settings.setCdTerm(accountSettingDto.getCdTerm());
        settings.setInterestRate(accountSettingDto.getInterestRate());
        return accountBankSettingRepository.save(settings);
    }
}
