package com.finStream.bankmanagementservice.mapper;

import com.finStream.bankmanagementservice.dto.Bank;
import com.finStream.bankmanagementservice.dto.BankDto;
import com.finStream.bankmanagementservice.entity.BankEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BankMapper {

    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    BankEntity mapBankDtoToBank(Bank bankDto);
    Bank mapBankToBankDto(BankEntity bank);
    Bank mapBankRequestToBankDto(BankDto bankRequest);
    BankEntity mapBankRequestToBank(BankDto bankRequest);
}
