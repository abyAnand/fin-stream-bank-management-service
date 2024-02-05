package com.finStream.bankmanagementservice.advice;

import com.finStream.bankmanagementservice.dto.ApiError;
import com.finStream.bankmanagementservice.exception.bank.BankNotFoundException;
import com.finStream.bankmanagementservice.exception.loan.LoanSettingNotFoundException;
import com.finStream.bankmanagementservice.exception.loan.LoanTypeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Slf4j
@ControllerAdvice
public class LoanExceptionHandler {

    @ExceptionHandler(LoanSettingNotFoundException.class)
    public ResponseEntity<ApiError> handleLoanSettingFoundException(
            BankNotFoundException ex,
            WebRequest request
    ){
        log.error("LoanSetting not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                LocalDate.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanTypeNotFoundException.class)
    public ResponseEntity<ApiError> handleLoanTypeFoundException(
            BankNotFoundException ex,
            WebRequest request
    ){
        log.error("Loan Type not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                LocalDate.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
