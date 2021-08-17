package com.moneytap.exception;

public class BeneficiaryAccountNotFoundException extends Exception{
    public BeneficiaryAccountNotFoundException() {
    }

    public BeneficiaryAccountNotFoundException(String message) {
        super(message);
    }
}
