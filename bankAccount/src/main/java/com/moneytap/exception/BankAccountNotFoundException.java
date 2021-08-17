package com.moneytap.exception;

public class BankAccountNotFoundException extends Exception {
    public BankAccountNotFoundException() {
    }

    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
