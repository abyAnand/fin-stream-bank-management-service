package com.finStream.bankmanagementservice.mapper;


import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.accountSetting.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountSettingDto mapAccountSettingToAccountSettingDto(AccountSetting accountSetting);

    AccountSettingDto mapCheckingAccountsSettingToAccountSettingDto(CheckingAccountsSetting bankSetting);

    AccountSettingDto mapFDAccountsSettingToAccountSettingDto(FDAccountsSetting bankSetting);

    AccountSettingDto mapJointAccountsSettingToAccountSettingDto(JointAccountsSetting bankSetting);

    AccountSettingDto mapMoneyMarketAccountsSettingToAccountSettingDto(MoneyMarketAccountsSetting bankSetting);

    AccountSettingDto mapSavingsAccountsSettingToAccountSettingDto(SavingsAccountsSetting bankSetting);

}
