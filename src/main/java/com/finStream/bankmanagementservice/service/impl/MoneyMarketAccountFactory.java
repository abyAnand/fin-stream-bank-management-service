package com.finStream.bankmanagementservice.service.impl;

import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.entity.bankSetting.MoneyMarketAccountsBankSetting;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
public class MoneyMarketAccountFactory extends AbstractBankSettingFactory {
    public MoneyMarketAccountFactory(AccountBankSettingRepository accountBankSettingRepository) {
        super(accountBankSettingRepository);
    }

    @Override
    public AccountBankSetting createBankSetting(BankSettingDto bankSettingDto) {
        MoneyMarketAccountsBankSetting settings = new MoneyMarketAccountsBankSetting();
        setCommonFields(settings, bankSettingDto);
        settings.setMaxMonthlyTransactions(bankSettingDto.getMaxMonthlyTransactions());
        return accountBankSettingRepository.save(settings);
    }

    /**
     * @param bankSettingDto
     * @return
     */
    @Override
    public AccountBankSetting updateBankSetting(BankSettingDto bankSettingDto) {
        MoneyMarketAccountsBankSetting settings = accountBankSettingRepository.findMoneyMarketAccountsBankSettingById(bankSettingDto.getId());
        setCommonFields(settings, bankSettingDto);
        settings.setMaxMonthlyTransactions(bankSettingDto.getMaxMonthlyTransactions());
        return accountBankSettingRepository.save(settings);
    }
}
