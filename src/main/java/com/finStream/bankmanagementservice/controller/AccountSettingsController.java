package com.finStream.bankmanagementservice.controller;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.dto.account.AccountTypeInfoDto;
import com.finStream.bankmanagementservice.dto.account.AccountTypeListRequestDto;
import com.finStream.bankmanagementservice.service.account.AccountTypeService;
import com.finStream.bankmanagementservice.service.account.impl.AccountSettingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts/settings")
@RequiredArgsConstructor
public class AccountSettingsController {

    private final AccountTypeService accountTypeService;
    private final AccountSettingServiceImpl accountSettingService;

    @PostMapping
    public ResponseEntity<AccountSettingDto> createAccountSetting(@RequestBody AccountSettingDto accountSettingDto){
        return ResponseEntity.ok(accountSettingService.createAccountSetting(accountSettingDto));
    }

    @GetMapping("/{bankId}")
    public ResponseEntity<List<AccountTypeInfoDto>> getAllAccountTypes(@PathVariable UUID bankId){
        return ResponseEntity.ok(accountTypeService.getAccountTypesWithCount(bankId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<AccountSettingDto>> getAccountListByType(@RequestParam String bankId, @RequestParam String accountType){
        AccountTypeListRequestDto requestDto = new AccountTypeListRequestDto(UUID.fromString(bankId), accountType);
        List<AccountSettingDto> accountSettingListByAccountType = accountTypeService.getAccountSettingListByAccountType(requestDto);
        return ResponseEntity.ok(accountSettingListByAccountType);
    }

    @PutMapping
    public ResponseEntity<AccountSettingDto> updateAccountSetting(@RequestBody AccountSettingDto accountSettingDto){
        return ResponseEntity.ok(accountSettingService.updateAccountSetting(accountSettingDto));
    }
}
