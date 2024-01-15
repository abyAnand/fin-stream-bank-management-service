package com.finStream.bankmanagementservice.service.impl;

import com.finStream.bankmanagementservice.dto.Bank;
import com.finStream.bankmanagementservice.dto.BankDto;
import com.finStream.bankmanagementservice.dto.VerifyBankDto;
import com.finStream.bankmanagementservice.entity.BankEntity;
import com.finStream.bankmanagementservice.enums.Status;
import com.finStream.bankmanagementservice.exception.BankNameConflictException;
import com.finStream.bankmanagementservice.exception.BankNotFoundException;
import com.finStream.bankmanagementservice.exception.BankShortNameConflictException;
import com.finStream.bankmanagementservice.mapper.BankMapper;
import com.finStream.bankmanagementservice.repository.BankRepository;
import com.finStream.bankmanagementservice.service.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    public Bank createBank(BankDto bankRequest) {
        if(bankNameExists(bankRequest.getName())){
            throw new BankNameConflictException(bankRequest.getName() + ": bank Name already exists");
        }
        if(bankShortNameExists(bankRequest.getShortName())){
            throw new BankShortNameConflictException(bankRequest.getShortName() + " : bank short Name already exists");
        }

        BankEntity bank = BankMapper.INSTANCE.mapBankRequestToBank(bankRequest);
        bank.setVerified(true);
        bank.setStatus(Status.PENDING_APPROVAL);
        bank.setCreatedDate(LocalDateTime.now());
        BankEntity savedBank = bankRepository.save(bank);
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
    public Bank updateBank(Bank bankDto) {
        BankEntity existingBank = getBankOrThrow(bankDto.getId());
        validateBankName(bankDto, existingBank);
        validateBankShortName(bankDto, existingBank);

        BankEntity updatedBank = bankMapper.mapBankDtoToBank(bankDto);
        BankEntity savedBank = bankRepository.save(updatedBank);

        return bankMapper.mapBankToBankDto(savedBank);
    }

    @Override
    public boolean verifyBank(VerifyBankDto verifyBankDto) {
        BankEntity bank = bankRepository.findById(verifyBankDto.getBankId()).orElseThrow(
                () -> new BankNotFoundException("User not found with id: " + verifyBankDto.getBankId())
        );

        bank.setVerified(true);
        return true;
    }


    private BankEntity getBankOrThrow(UUID bankId) {
        return bankRepository.findById(bankId)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with id: " + bankId));
    }

    private void validateBankName(Bank bankDto, BankEntity existingBank) {
        bankRepository.findByName(bankDto.getName())
                .ifPresent(bank -> {
                    if (!bank.getId().equals(existingBank.getId())) {
                        throw new BankNameConflictException("Bank with name '" + bankDto.getName() + "' already exists.");
                    }
                });
    }

    private void validateBankShortName(Bank bankDto, BankEntity existingBank) {
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
    public Bank getBank(UUID bankId) {
        BankEntity bank = bankRepository.findById(bankId)
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
    public List<Bank> findAllBanks() {
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
    private boolean isNotDeleted(BankEntity bank){
        return !bank.isDeleted();
    }

    boolean bankNameExists(String bankName) {
        return bankRepository.findByName(bankName).isPresent();
    }

    boolean bankShortNameExists(String bankShortName) {
        return bankRepository.findByShortName(bankShortName).isPresent();
    }
}
