package com.moneytap.exception;

public class BankAccountMismatchException extends Exception{
    public BankAccountMismatchException() {
    }

    public BankAccountMismatchException(String message) {
        super(message);
    }
}
