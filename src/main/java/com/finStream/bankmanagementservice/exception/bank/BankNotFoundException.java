package com.finStream.bankmanagementservice.exception.bank;

public class BankNotFoundException extends RuntimeException{

    public BankNotFoundException(String message){
        super(message);
    }
}
