package com.finStream.bankmanagementservice.service.account.impl;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.enums.AccountType;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
import com.finStream.bankmanagementservice.service.account.interfaces.AccountSettingFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountSettingDtoHandler {
    private final Map<AccountType, AccountSettingFactory> factoryMap;

    public AccountSettingDtoHandler(AccountBankSettingRepository accountBankSettingRepository) {
        factoryMap = new HashMap<>();
        factoryMap.put(AccountType.SAVINGS, new SavingsAccountFactory(accountBankSettingRepository));
        factoryMap.put(AccountType.FD, new FDAccountFactory(accountBankSettingRepository));
        factoryMap.put(AccountType.CHECKING, new CheckingAccountFactory(accountBankSettingRepository));
        factoryMap.put(AccountType.JOINT, new JointAccountFactory(accountBankSettingRepository));
        factoryMap.put(AccountType.MONEY_MARKET, new MoneyMarketAccountFactory(accountBankSettingRepository));
    }

    public AccountSetting createAccountSetting(AccountSettingDto accountSettingDto) {
        AccountSettingFactory factory = factoryMap.get(accountSettingDto.getAccountType());
        if (factory != null) {
            return factory.createBankSetting(accountSettingDto);
        }
        return null;
    }

    public AccountSetting updateAccountSetting(AccountSettingDto accountSettingDto) {
        AccountSettingFactory factory = factoryMap.get(accountSettingDto.getAccountType());
        if (factory != null) {
            return factory.updateBankSetting(accountSettingDto);
        }
        return null;
    }


}
