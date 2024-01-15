package com.finStream.bankmanagementservice.controller;

import com.finStream.bankmanagementservice.dto.AccountTypeDto;
import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts/settings")
@RequiredArgsConstructor
public class BankAccountSettingsController {

    private final AccountTypeService accountTypeService;

    @GetMapping
    public ResponseEntity<List<AccountTypeDto>> getAllAccountTypes(){
        return ResponseEntity.ok(accountTypeService.getAccountTypes());
    }
}
