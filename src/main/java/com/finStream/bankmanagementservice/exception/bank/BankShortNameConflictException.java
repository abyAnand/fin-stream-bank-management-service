package com.finStream.bankmanagementservice.exception.bank;

public class BankShortNameConflictException extends RuntimeException{
    public BankShortNameConflictException(String message){
        super(message);
    }
}
