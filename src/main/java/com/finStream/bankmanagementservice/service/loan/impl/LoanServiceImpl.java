package com.finStream.bankmanagementservice.service.loan.impl;

import com.finStream.bankmanagementservice.dto.loan.LoanSettingDto;
import com.finStream.bankmanagementservice.dto.loan.LoanTypeDto;
import com.finStream.bankmanagementservice.entity.loan.LoanSetting;
import com.finStream.bankmanagementservice.entity.loan.LoanType;
import com.finStream.bankmanagementservice.exception.loan.LoanSettingNotFoundException;
import com.finStream.bankmanagementservice.exception.loan.LoanTypeNotFoundException;
import com.finStream.bankmanagementservice.mapper.LoanMapper;
import com.finStream.bankmanagementservice.mapper.LoanTypeMapper;
import com.finStream.bankmanagementservice.repository.LoanSettingRepository;
import com.finStream.bankmanagementservice.repository.LoanTypeRepository;
import com.finStream.bankmanagementservice.service.loan.ILoanService;
import com.finStream.bankmanagementservice.service.loanType.LoanTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private final LoanSettingRepository loanSettingRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final LoanMapper loanMapper;
    private final LoanTypeMapper loanTypeMapper;

    /**
     * @param loanSettingDto
     * @return
     */
    @Override
    public LoanSettingDto createLoanSetting(LoanSettingDto loanSettingDto) {
        LoanSetting loanSetting = convertToEntity(loanSettingDto);
        loanSetting.setLoanTypeId(loanSettingDto.getLoanTypeDto().getId());
        LoanSettingDto savedLoanSettingDto = convertToDto(loanSettingRepository.save(loanSetting));
        savedLoanSettingDto.setLoanTypeDto(getLoanTypeDtoById(loanSetting.getLoanTypeId()));
        return savedLoanSettingDto;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public LoanSettingDto getLoanSettingById(UUID id) {
        LoanSetting loanSetting = loanSettingRepository.findById(id)
                .orElseThrow(() -> new LoanSettingNotFoundException("LoanSetting not found with id: " + id));
        LoanSettingDto loanSettingDto = convertToDto(loanSetting);
        loanSettingDto.setLoanTypeDto(getLoanTypeDtoById(loanSetting.getLoanTypeId()));
        return loanSettingDto;
    }

    /**
     * @param loanTypeId
     * @return
     */
    @Override
    public List<LoanSettingDto> getAllLoanSettingsByLoanTypeId(UUID loanTypeId) {
        return loanSettingRepository.findAllByLoanTypeId(loanTypeId)
                .stream()
                .map(loanSetting -> {
                    LoanSettingDto loanSettingDto = convertToDto(loanSetting);
                    loanSettingDto.setLoanTypeDto(getLoanTypeDtoById(loanSetting.getLoanTypeId()));
                    return loanSettingDto;
                })
                .toList();
    }

    /**
     * @param loanSettingDto
     * @return
     */
    @Override
    public LoanSettingDto updateLoanSetting(LoanSettingDto loanSettingDto) {
        return loanSettingRepository.findById(loanSettingDto.getId())
                .map(loanSetting -> {
                    LoanSetting updatedLoanSettingEntity = loanSettingRepository.save(
                            convertAndSetLoanTypeId(loanSettingDto, loanSetting.getLoanTypeId())
                    );
                    return setLoanTypeDtoAndConvertToDto(updatedLoanSettingEntity);
                })
                .orElse(null);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteLoanSetting(UUID id) {
        return loanSettingRepository.findById(id)
                .map(loanSetting -> {
                    loanSetting.setDeleted(true);
                    loanSettingRepository.save(loanSetting);
                    return true;
                })
                .orElseThrow(() -> new LoanSettingNotFoundException("Could not find Loan Setting with id: "+ id));
    }

    private LoanTypeDto getLoanTypeDtoById(UUID loanTypeId) {
        LoanType loanType = loanTypeRepository.findById(loanTypeId)
                .orElseThrow(() -> new LoanTypeNotFoundException("Loan type not found with id " + loanTypeId));
        return convertLoanToDto(loanType);
    }

    public LoanSettingDto convertToDto(LoanSetting loanSetting){
        return loanMapper.mapLoanSettingToLoanSettingDto(loanSetting);
    }

    public LoanSetting convertToEntity(LoanSettingDto loanSettingDto){
        return loanMapper.mapLoanSettingDtoToLoanSetting(loanSettingDto);
    }

    private LoanSetting convertAndSetLoanTypeId(LoanSettingDto loanSettingDto, UUID loanTypeId) {
        LoanSetting loanSettingEntity = convertToEntity(loanSettingDto);
        loanSettingEntity.setLoanTypeId(loanTypeId);
        return loanSettingEntity;
    }

    private LoanSettingDto setLoanTypeDtoAndConvertToDto(LoanSetting updatedLoanSettingEntity) {
        LoanTypeDto loanTypeDto = getLoanTypeDtoById(updatedLoanSettingEntity.getLoanTypeId());
        LoanSettingDto updatedLoanSettingDto = convertToDto(updatedLoanSettingEntity);
        updatedLoanSettingDto.setLoanTypeDto(loanTypeDto);
        return updatedLoanSettingDto;
    }

    public LoanTypeDto convertLoanToDto(LoanType loanType){
        return loanTypeMapper.mapLoanTypeToLoanTypeDto(loanType);
    }

    public LoanType convertLoanDtoToEntity(LoanTypeDto loanTypeDto){
        return loanTypeMapper.mapLoanTypeDtoToLoanType(loanTypeDto);
    }
}
