package com.moneytap.exception;

public class InsufficientFundsWalletException extends Exception {
    public InsufficientFundsWalletException() {
    }

    public InsufficientFundsWalletException(String message) {
        super(message);
    }
}
