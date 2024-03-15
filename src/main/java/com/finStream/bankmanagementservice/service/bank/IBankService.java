package com.finStream.bankmanagementservice.service.bank;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.dto.bank.Bank;
import com.finStream.bankmanagementservice.dto.bank.BankDto;
import com.finStream.bankmanagementservice.dto.bank.VerifyBankDto;

import java.util.List;
import java.util.UUID;

/**
 * The IBankService interface defines the contract for performing operations related to banks
 * in the application. Implementations of this interface provide functionality for creating,
 * updating, retrieving, deleting banks, and retrieving a list of all banks.
 */
public interface IBankService {

    /**
     * Creates a new bank based on the provided bank request data.
     *
     * @param bankRequest The object containing bank data to be saved.
     * @return The newly created bank as a BankDto object.
     */
    Bank createBank(BankDto bankRequest);

    /**
     * Updates a bank based on the provided bank data (DTO).
     *
     * @param bankDto The DTO object containing updated bank data.
     * @return The updated bank as a BankDto object.
     */
    Bank updateBank(Bank bankDto);

    boolean verifyBank(UUID bankId);

    /**
     * Retrieves a bank by its unique identifier (bankId).
     *
     * @param bankId The unique identifier of the bank to retrieve.
     * @return The bank as a BankDto object with the specified bankId.
     */
    Bank getBank(UUID bankId);

    /**
     * Deletes a bank with the specified bankId.
     *
     * @param bankId The unique identifier of the bank to be deleted.
     * @return A boolean indicating whether the bank was successfully deleted.
     */
    boolean deleteBank(UUID bankId);

    /**
     * Retrieves a list of all banks in the system.
     *
     * @return A list of BankDto objects representing all banks.
     */
    List<Bank> findAllBanks();

    List<AccountSettingDto> findAllAccountsByBankId(UUID bankId);
}
