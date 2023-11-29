package com.finStream.bankmanagementservice.advice;

import com.finStream.bankmanagementservice.dto.ApiError;
import com.finStream.bankmanagementservice.exception.BankNameConflictException;
import com.finStream.bankmanagementservice.exception.BankNotFoundException;
import com.finStream.bankmanagementservice.exception.BankShortNameConflictException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Slf4j
@ControllerAdvice
public class BankExceptionHandler {

    @ExceptionHandler(BankNameConflictException.class)
    public ResponseEntity<ApiError> handleBankNameConflictException(
            BankNameConflictException ex,
            WebRequest request){
        log.error("Bank name already exists: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                LocalDate.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(BankShortNameConflictException.class)
    public ResponseEntity<ApiError> handleBankShortNameConflictException(
            BankShortNameConflictException ex,
            WebRequest request){
        log.error("Bank short name already exists: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                LocalDate.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(BankNotFoundException.class)
    public ResponseEntity<ApiError> handleBankNotFoundException(
            BankNotFoundException ex,
            WebRequest request
    ){
        log.error("Bank not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                LocalDate.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }


}
