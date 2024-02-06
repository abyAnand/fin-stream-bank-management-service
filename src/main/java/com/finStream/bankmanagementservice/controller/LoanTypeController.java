package com.finStream.bankmanagementservice.controller;

import com.finStream.bankmanagementservice.dto.loan.LoanTypeDto;

import com.finStream.bankmanagementservice.service.loanType.LoanTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loan/type")
@RequiredArgsConstructor
public class LoanTypeController {

    private final LoanTypeServiceImpl loanTypeSevice;

    @PostMapping
    public ResponseEntity<LoanTypeDto> createLoanType(@RequestBody LoanTypeDto loanSettingDto){
        return ResponseEntity.ok(loanTypeSevice.createLoanType(loanSettingDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<LoanTypeDto>> getAllLoanTypes(){
        return ResponseEntity.ok(loanTypeSevice.getAllLoanTypes());
    }

    @GetMapping("/list/{bankId}")
    public ResponseEntity<List<LoanTypeDto>> getLoanTypeList(@PathVariable UUID bankId){
        return ResponseEntity.ok(loanTypeSevice.getAllLoanTypeByBankId(bankId));
    }

    @GetMapping("/{loanTypeId}")
    public ResponseEntity<LoanTypeDto> getLoanType(@PathVariable UUID loanTypeId){
        return ResponseEntity.ok(loanTypeSevice.getLoanTypeById(loanTypeId));
    }

    @PutMapping
    public ResponseEntity<LoanTypeDto> updateLoanSetting(LoanTypeDto loanTypeDto){
        return ResponseEntity.ok(loanTypeSevice.updateLoanType(loanTypeDto));
    }

    @DeleteMapping("/{loanTypeId}")
    public boolean deleteLoanSetting(@PathVariable UUID loanTypeId){
        return loanTypeSevice.deleteLoanType(loanTypeId);
    }
}
