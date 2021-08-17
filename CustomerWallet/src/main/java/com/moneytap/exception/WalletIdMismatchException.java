package com.moneytap.exception;

public class WalletIdMismatchException extends Exception {
    public WalletIdMismatchException() {
    }

    public WalletIdMismatchException(String message) {
        super(message);
    }
}
