package com.finStream.bankmanagementservice.exception;

public class BankNameConflictException extends RuntimeException{
    public BankNameConflictException(String message){
        super(message);
    }
}
