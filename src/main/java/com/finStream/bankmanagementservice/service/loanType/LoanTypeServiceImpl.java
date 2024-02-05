package com.finStream.bankmanagementservice.service.loanType;

import com.finStream.bankmanagementservice.dto.loan.LoanSettingDto;
import com.finStream.bankmanagementservice.dto.loan.LoanTypeDto;
import com.finStream.bankmanagementservice.entity.loan.LoanSetting;
import com.finStream.bankmanagementservice.entity.loan.LoanType;
import com.finStream.bankmanagementservice.exception.loan.LoanSettingNotFoundException;
import com.finStream.bankmanagementservice.exception.loan.LoanTypeNotFoundException;
import com.finStream.bankmanagementservice.mapper.LoanTypeMapper;
import com.finStream.bankmanagementservice.repository.LoanSettingRepository;
import com.finStream.bankmanagementservice.repository.LoanTypeRepository;
import com.finStream.bankmanagementservice.service.loan.impl.LoanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements ILoanType{

    private final LoanTypeMapper loanTypeMapper;
    private final LoanTypeRepository loanTypeRepository;
    private final LoanSettingRepository loanSettingRepository;
    private final LoanServiceImpl loanService;
    /**
     * @param loanSettingDto
     * @return
     */
    @Override
    public LoanTypeDto createLoanType(LoanTypeDto loanSettingDto) {
        LoanType loanType = convertToEntity(loanSettingDto);
        return convertToDto(loanTypeRepository.save(loanType));
    }
    /**
     * @param id
     * @return
     */
    @Override
    public LoanTypeDto getLoanTypeById(UUID id) {
        LoanType loanType = loanTypeRepository.findById(id)
                .orElseThrow(() -> new LoanSettingNotFoundException("LoanSetting not found with id: " + id));

        LoanTypeDto loanTypeDto = convertToDto(loanType);
        loanTypeDto.setLoanSettingList(fetchLoanSettingList(id));

        return loanTypeDto;
    }

    /**
     * @param bankId
     * @return
     */
    @Override
    public List<LoanTypeDto> getAllLoanTypeByBankId(UUID bankId) {

        return loanTypeRepository.findAllByBankId(bankId)
                .stream()
                .map(this::convertToDto)
                .peek(loanTypeDto -> loanTypeDto.setLoanSettingList(fetchLoanSettingList(loanTypeDto.getId())))
                .toList();
    }

    /**
     * @param loanSettingDto
     * @return
     */
    @Override
    public LoanTypeDto updateLoanType(LoanTypeDto loanSettingDto) {
        Optional<LoanType> loanTypeOptional = loanTypeRepository.findById(loanSettingDto.getId());
        return loanTypeOptional.map(loanSetting -> {
            LoanType updatedLoanSettingEntity = loanTypeRepository.save(convertToEntity(loanSettingDto));
            return convertToDto(updatedLoanSettingEntity);
        }).orElse(null);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteLoanType(UUID id) {
        return loanTypeRepository.findById(id)
                .map(loanType -> {
                    loanType.setDeleted(true);
                    loanTypeRepository.save(loanType);
                    return true;
                })
                .orElseThrow(() -> new LoanTypeNotFoundException("Could not find Loan Type with id: "+ id));
    }

    public LoanTypeDto convertToDto(LoanType loanType){
        return loanTypeMapper.mapLoanTypeToLoanTypeDto(loanType);
    }

    public LoanType convertToEntity(LoanTypeDto loanTypeDto){
        return loanTypeMapper.mapLoanTypeDtoToLoanType(loanTypeDto);
    }

    private List<LoanSettingDto> fetchLoanSettingList(UUID loanTypeId) {
        return loanSettingRepository
                .findAllByLoanTypeId(loanTypeId)
                .stream()
                .map(loanService::convertToDto)
                .toList();
    }

}
