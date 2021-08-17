package com.moneytap.exception;

public class InvalidWalletException extends Exception {
    public InvalidWalletException() {
    }

    public InvalidWalletException(String message) {
        super(message);
    }
}
