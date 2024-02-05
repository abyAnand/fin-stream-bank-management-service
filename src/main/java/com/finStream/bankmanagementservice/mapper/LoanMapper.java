package com.finStream.bankmanagementservice.mapper;

import com.finStream.bankmanagementservice.dto.loan.LoanSettingDto;
import com.finStream.bankmanagementservice.entity.loan.LoanSetting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    LoanSetting mapLoanSettingDtoToLoanSetting(LoanSettingDto loanSettingDto);
    LoanSettingDto mapLoanSettingToLoanSettingDto(LoanSetting loanSetting);
}
