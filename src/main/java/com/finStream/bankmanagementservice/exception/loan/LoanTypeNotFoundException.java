package com.finStream.bankmanagementservice.exception.loan;

public class LoanTypeNotFoundException extends RuntimeException{

    public LoanTypeNotFoundException(String message){
        super(message);
    }
}
