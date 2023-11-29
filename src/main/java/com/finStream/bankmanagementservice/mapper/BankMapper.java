package com.finStream.bankmanagementservice.mapper;

import com.finStream.bankmanagementservice.dto.BankDto;
import com.finStream.bankmanagementservice.dto.BankRequest;
import com.finStream.bankmanagementservice.entity.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BankMapper {

    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    Bank mapBankDtoToBank(BankDto bankDto);
    BankDto mapBankToBankDto(Bank bank);
    BankDto mapBankRequestToBankDto(BankRequest bankRequest);
    Bank mapBankRequestToBank(BankRequest bankRequest);
}
