package com.finStream.bankmanagementservice.service.account.impl;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.accountSetting.*;
import com.finStream.bankmanagementservice.enums.AccountType;

import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
import com.finStream.bankmanagementservice.service.account.interfaces.IAccountSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountSettingServiceImpl implements IAccountSettingService {


    private final AccountBankSettingRepository accountBankSettingRepository;
    private final AccountSettingDtoHandler accountSettingDtoHandler;

        /**
         * @param accountSettingDto
         * @return
         */
        @Override
        public AccountSettingDto createAccountSetting(AccountSettingDto accountSettingDto) {
            AccountSetting createdSetting = accountSettingDtoHandler.createAccountSetting(accountSettingDto);
            return convertToDto(createdSetting);
        }

    /**
     * @param accountSettingDto
     * @return
     */
    @Override
    public AccountSettingDto updateAccountSetting(AccountSettingDto accountSettingDto) {
        AccountSetting createdSetting = accountSettingDtoHandler.updateAccountSetting(accountSettingDto);
        return convertToDto(createdSetting);
    }

    private AccountSettingDto convertToDto(AccountSetting accountSetting) {
        if (accountSetting == null) {
            return null;
        }

        AccountSettingDto accountSettingDto = new AccountSettingDto();
        if(accountSetting.getId() != null){
            accountSettingDto.setId(accountSetting.getId());
        }
        accountSettingDto.setBankId(accountSetting.getBankId());
        accountSettingDto.setAccountName(accountSetting.getAccountName());

        // Set properties based on account type
        if (accountSetting instanceof SavingsAccountsSetting savingsSetting) {
            accountSettingDto.setInterestRate(savingsSetting.getInterestRate());
            accountSettingDto.setAccountType(AccountType.SAVINGS);

        } else if (accountSetting instanceof CheckingAccountsSetting checkingSetting) {
            accountSettingDto.setOverdraftLimit(checkingSetting.getOverdraftLimit());
            accountSettingDto.setAccountType(AccountType.CHECKING);

        } else if (accountSetting instanceof FDAccountsSetting fdSetting) {
            accountSettingDto.setCdTerm(fdSetting.getCdTerm());
            accountSettingDto.setInterestRate(fdSetting.getInterestRate());
            accountSettingDto.setAccountType(AccountType.FD);

        } else if (accountSetting instanceof JointAccountsSetting jointSetting) {
            accountSettingDto.setAccountHoldersLimit(jointSetting.getAccountHoldersLimit());
            accountSettingDto.setAccountType(AccountType.JOINT);

        } else if (accountSetting instanceof MoneyMarketAccountsSetting moneyMarketSetting) {
            accountSettingDto.setMaxMonthlyTransactions(moneyMarketSetting.getMaxMonthlyTransactions());
            accountSettingDto.setAccountType(AccountType.MONEY_MARKET);

        }

        return accountSettingDto;
    }

    @Override
    public AccountSettingDto getAccountSettingById(UUID accountId) {

        Optional<AccountSetting> accountSettingOptional = accountBankSettingRepository.findById(accountId);
        return accountSettingOptional.map(this::convertToDto).orElse(null);
    }



    }
