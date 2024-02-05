package com.finStream.bankmanagementservice.mapper;


import com.finStream.bankmanagementservice.dto.loan.LoanTypeDto;
import com.finStream.bankmanagementservice.entity.loan.LoanType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanTypeMapper {

    LoanTypeMapper INSTANCE = Mappers.getMapper(LoanTypeMapper.class);

    LoanType mapLoanTypeDtoToLoanType(LoanTypeDto loanTypeDto);
    LoanTypeDto mapLoanTypeToLoanTypeDto(LoanType loanType);
}
