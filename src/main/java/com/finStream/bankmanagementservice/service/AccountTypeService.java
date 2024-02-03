package com.finStream.bankmanagementservice.service;


import com.finStream.bankmanagementservice.dto.AccountTypeInfoDto;
import com.finStream.bankmanagementservice.dto.AccountTypeListRequestDto;
import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.entity.bankSetting.*;
import com.finStream.bankmanagementservice.enums.AccountType;

import com.finStream.bankmanagementservice.mapper.AccountMapper;
import com.finStream.bankmanagementservice.mapper.BankMapper;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class providing functionality related to account types and settings.
 */
@Service
@RequiredArgsConstructor
public class AccountTypeService {

    private final AccountBankSettingRepository accountBankSettingRepository;
    private final BankMapper bankMapper;
    private final AccountMapper accountMapper;

    /**
     * Retrieves a list of {@link BankSettingDto} based on the specified account type and bank ID.
     *
     * @param accountTypeListRequestDto DTO containing the bank ID and account type.
     * @return List of {@link BankSettingDto} corresponding to the given account type.
     */
    public List<BankSettingDto> getAccountSettingListByAccountType(AccountTypeListRequestDto accountTypeListRequestDto) {
        List<? extends AccountBankSetting> accountBankSettings =
                getAccountSettingByType(accountTypeListRequestDto.getBankId(), AccountType.valueOf(accountTypeListRequestDto.getAccountType()));

        String accountType = accountTypeListRequestDto.getAccountType();

        return accountBankSettings.stream()
                .map(accountBankSetting -> {
                    BankSettingDto bankSettingDto = mapAccountSettingToDto(accountBankSetting, accountType);
                    bankSettingDto.setAccountType(AccountType.valueOf(accountType));
                    System.out.println(bankSettingDto);
                    return bankSettingDto;
                })
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of {@link AccountTypeInfoDto} representing each account type along with its count for a given bank ID.
     *
     * @param bankId The unique identifier of the bank.
     * @return List of {@link AccountTypeInfoDto} containing account type information and counts.
     */
    public List<AccountTypeInfoDto> getAccountTypesWithCount(UUID bankId) {
        return Arrays.stream(AccountType.values())
                .map(accountType -> fetchCountAndCreateDto(bankId, accountType))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private AccountTypeInfoDto fetchCountAndCreateDto(UUID bankId, AccountType accountType) {
        List<? extends AccountBankSetting> accountBankSettings = getAccountSettingByType(bankId, accountType);
        long count = accountBankSettings.size();
        return new AccountTypeInfoDto(accountType.getDisplayName(), accountType.name(), (int) count);
    }

    private List<? extends AccountBankSetting> getAccountSettingByType(UUID bankId, AccountType accountType) {
        return switch (accountType) {
            case CHECKING -> accountBankSettingRepository.findAllCheckingAccountsBankSettingByBankId(bankId);
            case FD -> accountBankSettingRepository.findAllFDAccountsBankSettingByBankId(bankId);
            case JOINT -> accountBankSettingRepository.findAllJointAccountsBankSettingByBankId(bankId);
            case MONEY_MARKET -> accountBankSettingRepository.findAllMoneyMarketAccountsBankSettingByBankId(bankId);
            case SAVINGS -> accountBankSettingRepository.findAllSavingsAccountsBankSettingByBankId(bankId);
            default -> Collections.emptyList();
        };
    }

    private BankSettingDto mapAccountSettingToDto(AccountBankSetting accountBankSetting, String accountType) {
        switch (accountType) {
            case "CHECKING":
                return accountMapper.mapCheckingAccountsBankSettingToBankSettingDto((CheckingAccountsBankSetting) accountBankSetting);
            case "FD":
                return accountMapper.mapFDAccountsBankSettingToBankSettingDto((FDAccountsBankSetting) accountBankSetting);
            case "JOINT":
                return accountMapper.mapJointAccountsBankSettingToBankSettingDto((JointAccountsBankSetting) accountBankSetting);
            case "MONEY_MARKET":
                return accountMapper.mapMoneyMarketAccountsBankSettingToBankSettingDto((MoneyMarketAccountsBankSetting) accountBankSetting);
            case "SAVINGS":
                return accountMapper.mapSavingsAccountsBankSettingToBankSettingDto((SavingsAccountsBankSetting) accountBankSetting);
            default:
                throw new IllegalArgumentException("Unsupported account type: " + accountType);
        }
    }
}
