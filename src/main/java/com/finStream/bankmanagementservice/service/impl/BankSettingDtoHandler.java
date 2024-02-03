package com.finStream.bankmanagementservice.service.impl;

import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.enums.AccountType;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
import com.finStream.bankmanagementservice.service.interfaces.BankSettingFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankSettingDtoHandler {
    private final Map<AccountType, BankSettingFactory> factoryMap;

    public BankSettingDtoHandler(AccountBankSettingRepository accountBankSettingRepository) {
        factoryMap = new HashMap<>();
        factoryMap.put(AccountType.SAVINGS, new SavingsAccountFactory(accountBankSettingRepository));
        factoryMap.put(AccountType.FD, new FDAccountFactory(accountBankSettingRepository));
        factoryMap.put(AccountType.CHECKING, new CheckingAccountFactory(accountBankSettingRepository));
        factoryMap.put(AccountType.JOINT, new JointAccountFactory(accountBankSettingRepository));
        factoryMap.put(AccountType.MONEY_MARKET, new MoneyMarketAccountFactory(accountBankSettingRepository));
    }

    public AccountBankSetting createAccountSetting(BankSettingDto bankSettingDto) {
        BankSettingFactory factory = factoryMap.get(bankSettingDto.getAccountType());
        if (factory != null) {
            return factory.createBankSetting(bankSettingDto);
        }
        return null;
    }

    public AccountBankSetting updateAccountSetting(BankSettingDto bankSettingDto) {
        BankSettingFactory factory = factoryMap.get(bankSettingDto.getAccountType());
        if (factory != null) {
            return factory.updateBankSetting(bankSettingDto);
        }
        return null;
    }


}
