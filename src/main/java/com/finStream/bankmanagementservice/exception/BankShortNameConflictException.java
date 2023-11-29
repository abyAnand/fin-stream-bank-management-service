package com.finStream.bankmanagementservice.exception;

public class BankShortNameConflictException extends RuntimeException{
    public BankShortNameConflictException(String message){
        super(message);
    }
}
