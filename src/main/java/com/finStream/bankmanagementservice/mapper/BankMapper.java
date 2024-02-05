package com.finStream.bankmanagementservice.mapper;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.dto.bank.Bank;
import com.finStream.bankmanagementservice.dto.bank.BankDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.bank.BankEntity;
import com.finStream.bankmanagementservice.entity.accountSetting.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BankMapper {

    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    BankEntity mapBankDtoToBank(Bank bankDto);
    Bank mapBankToBankDto(BankEntity bank);
    Bank mapBankRequestToBankDto(BankDto bankRequest);
    BankEntity mapBankRequestToBank(BankDto bankRequest);

    AccountSettingDto mapAccountSettingToAccountSettingDto(AccountSetting accountSetting);

    AccountSettingDto mapCheckingAccountsSettingToAccountSettingDto(CheckingAccountsSetting bankSetting);

    AccountSettingDto mapFDAccountsSettingToAccountSettingDto(FDAccountsSetting bankSetting);

    AccountSettingDto mapJointAccountsSettingToAccountSettingDto(JointAccountsSetting bankSetting);

    AccountSettingDto mapMoneyMarketAccountsSettingToAccountSettingDto(MoneyMarketAccountsSetting bankSetting);

    AccountSettingDto mapSavingsAccountsSettingToAccountSettingDto(SavingsAccountsSetting bankSetting);

}
