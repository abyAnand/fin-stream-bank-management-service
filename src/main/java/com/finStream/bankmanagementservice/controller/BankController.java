package com.finStream.bankmanagementservice.controller;

import com.finStream.bankmanagementservice.dto.BankDto;
import com.finStream.bankmanagementservice.dto.BankRequest;
import com.finStream.bankmanagementservice.service.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private final IBankService bankService;


    /**
     * Creates a new bank based on the provided request data.
     *
     * @param bankRequest The data transfer object containing bank details.
     * @return A ResponseEntity containing the created BankDto with status CREATED.
     */
    @PostMapping
    public ResponseEntity<BankDto> createBank(@RequestBody BankRequest bankRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bankService.createBank(bankRequest));
    }


    /**
     * Retrieves a bank by its unique identifier.
     *
     * @param bankId The UUID of the bank to retrieve.
     * @return A ResponseEntity containing the requested BankDto with status OK.
     */
    @GetMapping("/{bankId}")
    public ResponseEntity<BankDto> findBankById(@PathVariable UUID bankId){
        return ResponseEntity.ok(bankService.getBank(bankId));
    }


    /**
     * Updates the details of an existing bank.
     *
     * @param bankDto The data transfer object containing the updated bank details.
     * @return A ResponseEntity containing the updated BankDto with status OK.
     */
    @PutMapping
    public ResponseEntity<BankDto> updateBank(@RequestBody BankDto bankDto){
        return ResponseEntity.ok(bankService.updateBank(bankDto));
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
    public ResponseEntity<List<BankDto>> findAllBanks(){
        return ResponseEntity.ok(bankService.findAllBanks());
    }

}
