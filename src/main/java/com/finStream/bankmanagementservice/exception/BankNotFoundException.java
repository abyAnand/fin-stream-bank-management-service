package com.finStream.bankmanagementservice.exception;

public class BankNotFoundException extends RuntimeException{

    public BankNotFoundException(String message){
        super(message);
    }
}
