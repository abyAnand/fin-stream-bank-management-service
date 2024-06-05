package com.finStream.bankmanagementservice.service.account;


import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.dto.account.AccountTypeInfoDto;
import com.finStream.bankmanagementservice.dto.account.AccountTypeListRequestDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.accountSetting.*;
import com.finStream.bankmanagementservice.enums.AccountType;

import com.finStream.bankmanagementservice.mapper.AccountMapper;
import com.finStream.bankmanagementservice.mapper.BankMapper;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
import com.finStream.bankmanagementservice.service.image.ImageService;
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
    private final ImageService imageService;

    /**
     * Retrieves a list of {@link AccountSettingDto} based on the specified account type and bank ID.
     *
     * @param accountTypeListRequestDto DTO containing the bank ID and account type.
     * @return List of {@link AccountSettingDto} corresponding to the given account type.
     */
    public List<AccountSettingDto> getAccountSettingListByAccountType(AccountTypeListRequestDto accountTypeListRequestDto) {
        List<? extends AccountSetting> accountBankSettings =
                getAccountSettingByType(accountTypeListRequestDto.getBankId(), AccountType.valueOf(accountTypeListRequestDto.getAccountType()));

        String accountType = accountTypeListRequestDto.getAccountType();

        return accountBankSettings.stream()
                .map(accountBankSetting -> {
                    AccountSettingDto accountSettingDto = mapAccountSettingToDto(accountBankSetting, accountType);
                    accountSettingDto.setAccountType(AccountType.valueOf(accountType));
                    accountSettingDto.setImage(imageService.getOne(accountBankSetting.getImageId()));
                    return accountSettingDto;
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
        List<? extends AccountSetting> accountBankSettings = getAccountSettingByType(bankId, accountType);
        long count = accountBankSettings.size();
        return new AccountTypeInfoDto(accountType.getDisplayName(), accountType.name(), (int) count);
    }

    private List<? extends AccountSetting> getAccountSettingByType(UUID bankId, AccountType accountType) {
        return switch (accountType) {
            case CHECKING -> accountBankSettingRepository.findAllCheckingAccountsBankSettingByBankId(bankId);
            case FD -> accountBankSettingRepository.findAllFDAccountsBankSettingByBankId(bankId);
            case JOINT -> accountBankSettingRepository.findAllJointAccountsBankSettingByBankId(bankId);
            case MONEY_MARKET -> accountBankSettingRepository.findAllMoneyMarketAccountsBankSettingByBankId(bankId);
            case SAVINGS -> accountBankSettingRepository.findAllSavingsAccountsBankSettingByBankId(bankId);
            default -> Collections.emptyList();
        };
    }

    private AccountSettingDto mapAccountSettingToDto(AccountSetting accountSetting, String accountType) {
        switch (accountType) {
            case "CHECKING":
                return accountMapper.mapCheckingAccountsSettingToAccountSettingDto((CheckingAccountsSetting) accountSetting);
            case "FD":
                return accountMapper.mapFDAccountsSettingToAccountSettingDto((FDAccountsSetting) accountSetting);
            case "JOINT":
                return accountMapper.mapJointAccountsSettingToAccountSettingDto((JointAccountsSetting) accountSetting);
            case "MONEY_MARKET":
                return accountMapper.mapMoneyMarketAccountsSettingToAccountSettingDto((MoneyMarketAccountsSetting) accountSetting);
            case "SAVINGS":
                return accountMapper.mapSavingsAccountsSettingToAccountSettingDto((SavingsAccountsSetting) accountSetting);
            default:
                throw new IllegalArgumentException("Unsupported account type: " + accountType);
        }
    }
}
