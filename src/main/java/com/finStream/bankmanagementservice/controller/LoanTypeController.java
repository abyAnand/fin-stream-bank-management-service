package com.finStream.bankmanagementservice.controller;

import com.finStream.bankmanagementservice.dto.account.AccountSettingDto;
import com.finStream.bankmanagementservice.dto.loan.LoanTypeDto;

import com.finStream.bankmanagementservice.entity.Image;
import com.finStream.bankmanagementservice.entity.loan.LoanType;
import com.finStream.bankmanagementservice.service.image.ImageService;
import com.finStream.bankmanagementservice.service.loanType.LoanTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loan/type")
@RequiredArgsConstructor
public class LoanTypeController {

    private final LoanTypeServiceImpl loanTypeSevice;
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<LoanTypeDto> createLoanType(@RequestBody LoanTypeDto loanSettingDto){
        return ResponseEntity.ok(loanTypeSevice.createLoanType(loanSettingDto));
    }

    @PostMapping("/image")
    public ResponseEntity<LoanTypeDto> updateLoanTypeWithImage(@RequestPart("loanTypeId") String loanTypeId,
                                                                  @RequestPart("image" ) MultipartFile multipartFile) throws IOException {
        Image image = imageService.uploadAndSaveImage(multipartFile);
        LoanTypeDto loanTypeDto = loanTypeSevice.getLoanTypeById(UUID.fromString(loanTypeId));
        loanTypeDto.setImage(image);
        return ResponseEntity.ok(loanTypeSevice.updateLoanType(loanTypeDto));
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
