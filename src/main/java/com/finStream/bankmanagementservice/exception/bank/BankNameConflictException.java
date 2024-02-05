package com.finStream.bankmanagementservice.exception.bank;

public class BankNameConflictException extends RuntimeException{
    public BankNameConflictException(String message){
        super(message);
    }
}
