package com.finStream.bankmanagementservice.mapper;

import com.finStream.bankmanagementservice.dto.Bank;
import com.finStream.bankmanagementservice.dto.BankDto;
import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.entity.BankEntity;
import com.finStream.bankmanagementservice.entity.bankSetting.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BankMapper {

    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    BankEntity mapBankDtoToBank(Bank bankDto);
    Bank mapBankToBankDto(BankEntity bank);
    Bank mapBankRequestToBankDto(BankDto bankRequest);
    BankEntity mapBankRequestToBank(BankDto bankRequest);

    BankSettingDto mapAccountBankSettingToBankSettingDto(AccountBankSetting accountBankSetting);

    BankSettingDto mapCheckingAccountsBankSettingToBankSettingDto(CheckingAccountsBankSetting bankSetting);

    BankSettingDto mapFDAccountsBankSettingToBankSettingDto(FDAccountsBankSetting bankSetting);

    BankSettingDto mapJointAccountsBankSettingToBankSettingDto(JointAccountsBankSetting bankSetting);

    BankSettingDto mapMoneyMarketAccountsBankSettingToBankSettingDto(MoneyMarketAccountsBankSetting bankSetting);

    BankSettingDto mapSavingsAccountsBankSettingToBankSettingDto(SavingsAccountsBankSetting bankSetting);

}
