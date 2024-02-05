package com.finStream.bankmanagementservice.controller;

import com.finStream.bankmanagementservice.dto.loan.LoanSettingDto;
import com.finStream.bankmanagementservice.service.loan.impl.LoanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loan/settings")
@RequiredArgsConstructor
public class LoanSettingController {

    private final LoanServiceImpl loanService;

    @PostMapping
    public ResponseEntity<LoanSettingDto> createLoanSetting(@RequestBody LoanSettingDto loanSettingDto){
        return ResponseEntity.ok(loanService.createLoanSetting(loanSettingDto));
    }

    @GetMapping("/list/{loanTypeId}")
    public ResponseEntity<List<LoanSettingDto>> getLoanSettingList(@PathVariable UUID loanTypeId){
        return ResponseEntity.ok(loanService.getAllLoanSettingsByLoanTypeId(loanTypeId));
    }

    @GetMapping("/{loanSettingId}")
    public ResponseEntity<LoanSettingDto> getLoanSetting(@PathVariable UUID loanSettingId){
        return ResponseEntity.ok(loanService.getLoanSettingById(loanSettingId));
    }

    @PutMapping
    public ResponseEntity<LoanSettingDto> updateLoanSetting(@RequestBody LoanSettingDto loanSettingDto){
        return ResponseEntity.ok(loanService.updateLoanSetting(loanSettingDto));
    }

    @DeleteMapping("/{loanSettingId}")
    public boolean deleteLoanSetting(@PathVariable UUID loanSettingId){
        return loanService.deleteLoanSetting(loanSettingId);
    }


}
