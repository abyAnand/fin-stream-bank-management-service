package com.finStream.bankmanagementservice.mapper;


import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.entity.bankSetting.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    BankSettingDto mapAccountBankSettingToBankSettingDto(AccountBankSetting accountBankSetting);

    BankSettingDto mapCheckingAccountsBankSettingToBankSettingDto(CheckingAccountsBankSetting bankSetting);

    BankSettingDto mapFDAccountsBankSettingToBankSettingDto(FDAccountsBankSetting bankSetting);

    BankSettingDto mapJointAccountsBankSettingToBankSettingDto(JointAccountsBankSetting bankSetting);

    BankSettingDto mapMoneyMarketAccountsBankSettingToBankSettingDto(MoneyMarketAccountsBankSetting bankSetting);

    BankSettingDto mapSavingsAccountsBankSettingToBankSettingDto(SavingsAccountsBankSetting bankSetting);

}
