package com.finStream.bankmanagementservice.service.impl;

import com.finStream.bankmanagementservice.dto.BankDto;
import com.finStream.bankmanagementservice.dto.BankRequest;
import com.finStream.bankmanagementservice.entity.Bank;
import com.finStream.bankmanagementservice.enums.Status;
import com.finStream.bankmanagementservice.exception.BankNameConflictException;
import com.finStream.bankmanagementservice.exception.BankNotFoundException;
import com.finStream.bankmanagementservice.exception.BankShortNameConflictException;
import com.finStream.bankmanagementservice.mapper.BankMapper;
import com.finStream.bankmanagementservice.repository.BankRepository;
import com.finStream.bankmanagementservice.service.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Abi Anand <a href="mailto:abianand382@gmail.com"></a>"
 * @version 1.0.0
 */

/**
 * Service to handle bank CRUD operations
 */


@Service
@RequiredArgsConstructor
public class BankServiceImpl implements IBankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;
    /**
     * Creates a new bank based on the provided bank request data.
     *
     * @param bankRequest The object containing bank data to be saved.
     * @return The newly created bank as a BankDto object.
     */
    @Override
    public BankDto createBank(BankRequest bankRequest) {
        if(bankNameExists(bankRequest.getName())){
            throw new BankNameConflictException(bankRequest.getName() + ": bank Name already exists");
        }
        if(bankShortNameExists(bankRequest.getShortName())){
            throw new BankShortNameConflictException(bankRequest.getShortName() + " : bank short Name already exists");
        }

        Bank bank = BankMapper.INSTANCE.mapBankRequestToBank(bankRequest);
        bank.setVerified(false);
        bank.setStatus(Status.PENDING_APPROVAL);
        bank.setCreatedDate(LocalDateTime.now());
        Bank savedBank = bankRepository.save(bank);
        return bankMapper.mapBankToBankDto(savedBank);
    }

    /**
     * Updates an existing bank entity. Validates that the new name and shortname are unique.
     * @throws  BankNameConflictException  exception if the new name or shortname already exists for a different bank.
     * @throws BankNotFoundException exception if bank is not found
     *
     * @param bankDto The data transfer object containing the updated bank details.
     * @return The updated bank, mapped to a BankDto.
     */
    // Helper methods: getBankOrThrow, validateBankName, validateBankShortName
    @Override
    public BankDto updateBank(BankDto bankDto) {
        Bank existingBank = getBankOrThrow(bankDto.getId());
        validateBankName(bankDto, existingBank);
        validateBankShortName(bankDto, existingBank);

        Bank updatedBank = bankMapper.mapBankDtoToBank(bankDto);
        Bank savedBank = bankRepository.save(updatedBank);

        return bankMapper.mapBankToBankDto(savedBank);
    }

    private Bank getBankOrThrow(UUID bankId) {
        return bankRepository.findById(bankId)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with id: " + bankId));
    }

    private void validateBankName(BankDto bankDto, Bank existingBank) {
        bankRepository.findByName(bankDto.getName())
                .ifPresent(bank -> {
                    if (!bank.getId().equals(existingBank.getId())) {
                        throw new BankNameConflictException("Bank with name '" + bankDto.getName() + "' already exists.");
                    }
                });
    }

    private void validateBankShortName(BankDto bankDto, Bank existingBank) {
        bankRepository.findByShortName(bankDto.getShortName())
                .ifPresent(bank -> {
                    if (!bank.getId().equals(existingBank.getId())) {
                        throw new BankShortNameConflictException("Bank with shortname '" + bankDto.getShortName() + "' already exists.");
                    }
                });
    }

    /**
     * Retrieves the details of a bank identified by the given UUID.
     * @throws BankNotFoundException if the bank is not found.
     *
     * @param bankId The UUID of the bank to retrieve.
     * @return The bank details, mapped to a BankDto.
     */
    @Override
    public BankDto getBank(UUID bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with id: " + bankId));
        return bankMapper.mapBankToBankDto(bank);
    }

    /**
     * Performs a soft delete of the bank identified by the given UUID.
     * Sets the 'deleted' flag to true rather than removing the record from the database.
     * @throws BankNotFoundException if the bank is not found
     *
     * @param bankId The UUID of the bank to delete.
     * @return True if the bank was successfully marked as deleted.
     */
    @Override
    public boolean deleteBank(UUID bankId) {
        return bankRepository.findById(bankId)
                .map(bankTobeDeleted -> {
                    bankTobeDeleted.setDeleted(true);
                    bankRepository.save(bankTobeDeleted);
                    return true;
                        })
                .orElseThrow(() -> new BankNotFoundException("Could not find bank with id: "+ bankId));
    }

    /**
     * Retrieves a list of all banks that have not been marked as deleted.
     *
     * @return A list of all active banks, each mapped to a BankDto.
     */
    @Override
    public List<BankDto> findAllBanks() {
        return bankRepository.findAll()
                .stream()
                .filter(this::isNotDeleted)
                .map(bankMapper::mapBankToBankDto)
                .collect(Collectors.toList());
    }


    /**
     * Helper method to check if a bank is not marked as deleted.
     *
     * @param bank The bank entity to check.
     * @return True if the bank is not deleted, false otherwise.
     */
    private boolean isNotDeleted(Bank bank){
        return !bank.isDeleted();
    }

    boolean bankNameExists(String bankName) {
        return bankRepository.findByName(bankName).isPresent();
    }

    boolean bankShortNameExists(String bankShortName) {
        return bankRepository.findByShortName(bankShortName).isPresent();
    }
}
