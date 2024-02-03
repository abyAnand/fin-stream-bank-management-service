package com.finStream.bankmanagementservice.controller;

import com.finStream.bankmanagementservice.dto.*;
import com.finStream.bankmanagementservice.service.AccountTypeService;
import com.finStream.bankmanagementservice.service.impl.AccountSettingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts/settings")
@RequiredArgsConstructor
public class BankAccountSettingsController {

    private final AccountTypeService accountTypeService;
    private final AccountSettingServiceImpl accountSettingService;

    @PostMapping
    public ResponseEntity<BankSettingDto> createAccountSetting(@RequestBody BankSettingDto bankSettingDto){
        return ResponseEntity.ok(accountSettingService.createAccountSetting(bankSettingDto));
    }

    @GetMapping("/{bankId}")
    public ResponseEntity<List<AccountTypeInfoDto>> getAllAccountTypes(@PathVariable UUID bankId){
        return ResponseEntity.ok(accountTypeService.getAccountTypesWithCount(bankId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BankSettingDto>> getAccountListByType(@RequestParam String bankId, @RequestParam String accountType){
        AccountTypeListRequestDto requestDto = new AccountTypeListRequestDto(UUID.fromString(bankId), accountType);
        List<BankSettingDto> accountSettingListByAccountType = accountTypeService.getAccountSettingListByAccountType(requestDto);
        return ResponseEntity.ok(accountSettingListByAccountType);
    }

    @PutMapping
    public ResponseEntity<BankSettingDto> updateBankSetting(@RequestBody BankSettingDto bankSettingDto){
        return ResponseEntity.ok(accountSettingService.updateAccountSetting(bankSettingDto));
    }
}
