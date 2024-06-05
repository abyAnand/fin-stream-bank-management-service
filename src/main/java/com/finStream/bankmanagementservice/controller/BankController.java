package com.finStream.bankmanagementservice.controller;

import com.finStream.bankmanagementservice.dto.bank.Bank;
import com.finStream.bankmanagementservice.dto.bank.BankDto;
import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.dto.bank.BankInfoDto;
import com.finStream.bankmanagementservice.entity.Image;
import com.finStream.bankmanagementservice.entity.bank.BankEntity;
import com.finStream.bankmanagementservice.service.bank.impl.BankServiceImpl;
import com.finStream.bankmanagementservice.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * REST Controller for handling bank-related operations.
 * This controller provides endpoints for creating, retrieving, updating, and deleting banks.
 */

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {

    private final BankServiceImpl bankService;
    private final ImageService imageService;

    /**
     * Creates a new bank based on the provided request data.
     *
     * @param bankRequest The data transfer object containing bank details.
     * @return A ResponseEntity containing the created BankDto with status CREATED.
     */

    @PostMapping
    public ResponseEntity<Bank> createBank(@RequestBody BankDto bankRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bankService.createBank(bankRequest));
    }


    @PostMapping("/image")
    public ResponseEntity<Bank> updateAccountSettingWithImage(@RequestPart("bankId") String bankId,
                                                                           @RequestPart("image" ) MultipartFile multipartFile) throws IOException {
        Image image = imageService.uploadAndSaveImage(multipartFile);
        Bank bank = bankService.getBank(UUID.fromString(bankId));
        bank.setImage(image);
        return ResponseEntity.ok(bankService.updateBank(bank));
    }


    /**
     * Retrieves a bank by its unique identifier.
     *
     * @param bankId The UUID of the bank to retrieve.
     * @return A ResponseEntity containing the requested BankDto with status OK.
     */
    @GetMapping("/{bankId}")
    public ResponseEntity<Bank> findBankById(@PathVariable UUID bankId){
        return ResponseEntity.ok(bankService.getBank(bankId));
    }


    /**
     * Updates the details of an existing bank.
     *
     * @param bankDto The data transfer object containing the updated bank details.
     * @return A ResponseEntity containing the updated BankDto with status OK.
     */
    @PutMapping
    public ResponseEntity<Bank> updateBank(@RequestBody Bank bankDto){
        return ResponseEntity.ok(bankService.updateBank(bankDto));
    }

    @GetMapping("/verify/{bankId}")
    public ResponseEntity<Boolean> verifyBank(@PathVariable UUID bankId) {
        boolean verificationResult = bankService.verifyBank(bankId);
        return ResponseEntity.ok(verificationResult);
    }


    /**
     * Deletes a bank by its unique identifier.
     *
     * @param bankId The UUID of the bank to be deleted.
     * @return A ResponseEntity with status NO_CONTENT indicating successful deletion.
     */
    @DeleteMapping("/{bankId}")
    public ResponseEntity<Void> deletebank(@PathVariable UUID bankId){
        bankService.deleteBank(bankId);
        return ResponseEntity.noContent().build();
    }


    /**
     * Retrieves a list of all banks.
     *
     * @return A ResponseEntity containing a list of BankDto with status OK.
     */
    @GetMapping
    public ResponseEntity<List<Bank>> findAllBanks(){
        return ResponseEntity.ok(bankService.findAllBanks());
    }

    @GetMapping("/{bankId}/accounts")
    public List<AccountSettingDto> findAllAccountsByBankId(@PathVariable UUID bankId){
        return bankService.findAllAccountsByBankId(bankId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BankInfoDto>> getAllBankInfoList(){
        return ResponseEntity.ok(bankService.getAllBankInfoDto());
    }

    @GetMapping("/all/bank")
    public ResponseEntity<List<BankEntity>> getAllBankEntity(){
        return ResponseEntity.ok(bankService.getAllBankEntity());
    }

}
