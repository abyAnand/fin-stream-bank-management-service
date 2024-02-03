package com.finStream.bankmanagementservice.service.impl;

import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.entity.bankSetting.*;
import com.finStream.bankmanagementservice.enums.AccountType;

import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
import com.finStream.bankmanagementservice.service.interfaces.IAccountSettingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountSettingServiceImpl implements IAccountSettingService {


    private final AccountBankSettingRepository accountBankSettingRepository;
    private final BankSettingDtoHandler bankSettingDtoHandler;

        /**
         * @param bankSettingDto
         * @return
         */
        @Override
        public BankSettingDto createAccountSetting(BankSettingDto bankSettingDto) {
            AccountBankSetting createdSetting = bankSettingDtoHandler.createAccountSetting(bankSettingDto);
            return convertToDto(createdSetting);
        }

    /**
     * @param bankSettingDto
     * @return
     */
    @Override
    public BankSettingDto updateAccountSetting(BankSettingDto bankSettingDto) {
        AccountBankSetting createdSetting = bankSettingDtoHandler.updateAccountSetting(bankSettingDto);
        return convertToDto(createdSetting);
    }

    private BankSettingDto convertToDto(AccountBankSetting accountBankSetting) {
        if (accountBankSetting == null) {
            return null;
        }

        BankSettingDto bankSettingDto = new BankSettingDto();
        bankSettingDto.setBankId(accountBankSetting.getBankId());
        bankSettingDto.setAccountName(accountBankSetting.getAccountName());

        // Set properties based on account type
        if (accountBankSetting instanceof SavingsAccountsBankSetting savingsSetting) {
            bankSettingDto.setInterestRate(savingsSetting.getInterestRate());
            bankSettingDto.setAccountType(AccountType.SAVINGS);

        } else if (accountBankSetting instanceof CheckingAccountsBankSetting checkingSetting) {
            bankSettingDto.setOverdraftLimit(checkingSetting.getOverdraftLimit());
            bankSettingDto.setAccountType(AccountType.CHECKING);

        } else if (accountBankSetting instanceof FDAccountsBankSetting fdSetting) {
            bankSettingDto.setCdTerm(fdSetting.getCdTerm());
            bankSettingDto.setInterestRate(fdSetting.getInterestRate());
            bankSettingDto.setAccountType(AccountType.FD);

        } else if (accountBankSetting instanceof JointAccountsBankSetting jointSetting) {
            bankSettingDto.setAccountHoldersLimit(jointSetting.getAccountHoldersLimit());
            bankSettingDto.setAccountType(AccountType.JOINT);

        } else if (accountBankSetting instanceof MoneyMarketAccountsBankSetting moneyMarketSetting) {
            bankSettingDto.setMaxMonthlyTransactions(moneyMarketSetting.getMaxMonthlyTransactions());
            bankSettingDto.setAccountType(AccountType.MONEY_MARKET);

        }

        return bankSettingDto;
    }



    }
