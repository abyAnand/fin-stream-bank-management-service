package com.finStream.bankmanagementservice.service.bank.impl;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.dto.bank.Bank;
import com.finStream.bankmanagementservice.dto.bank.BankDto;
import com.finStream.bankmanagementservice.dto.bank.BankInfoDto;
import com.finStream.bankmanagementservice.dto.bank.VerifyBankDto;
import com.finStream.bankmanagementservice.dto.loan.LoanSettingDto;
import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.bank.BankEntity;
import com.finStream.bankmanagementservice.entity.accountSetting.*;
import com.finStream.bankmanagementservice.entity.loan.LoanSetting;
import com.finStream.bankmanagementservice.entity.loan.LoanType;
import com.finStream.bankmanagementservice.enums.AccountType;
import com.finStream.bankmanagementservice.enums.Status;
import com.finStream.bankmanagementservice.exception.bank.BankNameConflictException;
import com.finStream.bankmanagementservice.exception.bank.BankNotFoundException;
import com.finStream.bankmanagementservice.exception.bank.BankShortNameConflictException;
import com.finStream.bankmanagementservice.mapper.AccountMapper;
import com.finStream.bankmanagementservice.mapper.BankMapper;
import com.finStream.bankmanagementservice.mapper.LoanMapper;
import com.finStream.bankmanagementservice.mapper.LoanTypeMapper;
import com.finStream.bankmanagementservice.repository.AccountBankSettingRepository;
import com.finStream.bankmanagementservice.repository.BankRepository;
import com.finStream.bankmanagementservice.repository.LoanSettingRepository;
import com.finStream.bankmanagementservice.repository.LoanTypeRepository;
import com.finStream.bankmanagementservice.service.bank.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
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
    private final AccountMapper accountMapper;
    private final LoanMapper loanMapper;
    private final LoanTypeMapper loanTypeMapper;
    private final AccountBankSettingRepository accountBankSettingRepository;
    private final LoanSettingRepository loanSettingRepository;
    private final LoanTypeRepository loanTypeRepository;

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
    public boolean verifyBank(UUID bankId) {
        BankEntity bank = bankRepository.findById(bankId).orElseThrow(
                () -> new BankNotFoundException("User not found with id: " + bankId)
        );

        bank.setVerified(true);
        bankRepository.save(bank);
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

    public List<BankInfoDto> getAllBankInfoDto(){
        List<BankEntity> bank = bankRepository.findAll();
        List<BankInfoDto> list = bank.stream()
                .map(bankEntity -> getBankInfo(bankEntity.getId()))
                .toList();
        return list;
    }

    /**
     * Retrieves bank details along with associated account settings and loan settings.
     *
     * @param bankId The UUID of the bank to retrieve.
     * @return The bank details along with associated account settings and loan settings.
     */
    public BankInfoDto getBankInfo(UUID bankId) {
        BankEntity bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with id: " + bankId));

        Bank bankDto = bankMapper.mapBankToBankDto(bank);
        List<AccountSettingDto> accountSettings = findAllAccountsByBankId(bankId);
        List<LoanSettingDto> loanSettings = findAllLoanSettingsByBankId(bankId);

        return new BankInfoDto(
                bankDto.getId(),
                bankDto.getName(),
                bankDto.getShortName(),
                bankDto.getEmail(),
                bankDto.isVerified(),
                accountSettings,
                loanSettings
        );
    }

    /**
     * Retrieves all loan settings associated with a bank.
     *
     * @param bankId The UUID of the bank.
     * @return A list of loan settings associated with the bank.
     */
    public List<LoanSettingDto> findAllLoanSettingsByBankId(UUID bankId) {
        List<LoanType> loanTypeList = loanTypeRepository.findAllByBankId(bankId);

        return loanTypeList.stream()
                .flatMap(loanType -> loanSettingRepository.findAllByLoanTypeId(loanType.getId())
                        .stream()
                        .map(loanSetting -> {
                            LoanSettingDto loanSettingDto = loanMapper.mapLoanSettingToLoanSettingDto(loanSetting);
                            loanSettingDto.setLoanTypeDto(loanTypeMapper.mapLoanTypeToLoanTypeDto(loanType));
                            return loanSettingDto;
                        }))
                .collect(Collectors.toList());
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
     * @param bankId
     * @return
     */

    /**
     * @param bankId
     * @return
     */
    @Override
    public List<AccountSettingDto> findAllAccountsByBankId(UUID bankId) {
        List<AccountSettingDto> accountSettingDtoList = new ArrayList<>();

        for (AccountType accountType : AccountType.values()) {
            List<? extends AccountSetting> accountBankSettings = getAccountSettingByType(bankId, accountType);
            accountSettingDtoList.addAll(accountBankSettings.stream()
                    .map(accountBankSetting -> mapAccountSettingToDto(accountBankSetting, accountType.name()))
                    .toList());
        }

        accountSettingDtoList.forEach(System.out::println);

        return accountSettingDtoList;
    }

    @Override
    public List<BankEntity> getAllBankEntity() {
        return bankRepository.findAll();
    }

    private List<? extends AccountSetting> getAccountSettingByType(UUID bankId, AccountType accountType) {
        return switch (accountType) {
            case CHECKING -> accountBankSettingRepository.findAllCheckingAccountsBankSettingByBankId(bankId);
            case FD -> accountBankSettingRepository.findAllFDAccountsBankSettingByBankId(bankId);
            case JOINT -> accountBankSettingRepository.findAllJointAccountsBankSettingByBankId(bankId);
            case MONEY_MARKET -> accountBankSettingRepository.findAllMoneyMarketAccountsBankSettingByBankId(bankId);
            case SAVINGS -> accountBankSettingRepository.findAllSavingsAccountsBankSettingByBankId(bankId);
            default -> Collections.emptyList();
        };
    }

    private AccountSettingDto mapAccountSettingToDto(AccountSetting accountSetting, String accountType) {
        AccountSettingDto accountSettingDto;

        switch (accountType) {
            case "CHECKING":
                accountSettingDto = accountMapper.mapCheckingAccountsSettingToAccountSettingDto((CheckingAccountsSetting) accountSetting);
                break;
            case "FD":
                accountSettingDto = accountMapper.mapFDAccountsSettingToAccountSettingDto((FDAccountsSetting) accountSetting);
                break;
            case "JOINT":
                accountSettingDto = accountMapper.mapJointAccountsSettingToAccountSettingDto((JointAccountsSetting) accountSetting);
                break;
            case "MONEY_MARKET":
                accountSettingDto = accountMapper.mapMoneyMarketAccountsSettingToAccountSettingDto((MoneyMarketAccountsSetting) accountSetting);
                break;
            case "SAVINGS":
                accountSettingDto = accountMapper.mapSavingsAccountsSettingToAccountSettingDto((SavingsAccountsSetting) accountSetting);
                break;
            default:
                throw new IllegalArgumentException("Unsupported account type: " + accountType);
        }

        // Set the accountType in the BankSettingDto
        accountSettingDto.setAccountType(AccountType.valueOf(accountType));

        return accountSettingDto;
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
