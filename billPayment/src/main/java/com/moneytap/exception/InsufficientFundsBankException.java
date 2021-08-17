package com.moneytap.exception;

public class InsufficientFundsBankException extends Exception {
    public InsufficientFundsBankException() {
    }

    public InsufficientFundsBankException(String message) {
        super(message);
    }
}
