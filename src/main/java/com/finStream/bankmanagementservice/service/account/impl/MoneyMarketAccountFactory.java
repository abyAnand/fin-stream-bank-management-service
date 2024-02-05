package com.finStream.bankmanagementservice.service.account.impl;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.accountSetting.MoneyMarketAccountsSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
public class MoneyMarketAccountFactory extends AbstractAccountSettingFactory {
    public MoneyMarketAccountFactory(AccountBankSettingRepository accountBankSettingRepository) {
        super(accountBankSettingRepository);
    }

    @Override
    public AccountSetting createBankSetting(AccountSettingDto accountSettingDto) {
        MoneyMarketAccountsSetting settings = new MoneyMarketAccountsSetting();
        setCommonFields(settings, accountSettingDto);
        settings.setMaxMonthlyTransactions(accountSettingDto.getMaxMonthlyTransactions());
        return accountBankSettingRepository.save(settings);
    }

    /**
     * @param accountSettingDto
     * @return
     */
    @Override
    public AccountSetting updateBankSetting(AccountSettingDto accountSettingDto) {
        MoneyMarketAccountsSetting settings = accountBankSettingRepository.findMoneyMarketAccountsBankSettingById(accountSettingDto.getId());
        setCommonFields(settings, accountSettingDto);
        settings.setMaxMonthlyTransactions(accountSettingDto.getMaxMonthlyTransactions());
        return accountBankSettingRepository.save(settings);
    }
}
