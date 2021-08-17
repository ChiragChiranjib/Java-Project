package com.moneytap.exception;

public class DuplicateBeneficiaryException extends Exception {
    public DuplicateBeneficiaryException() {
    }

    public DuplicateBeneficiaryException(String message) {
        super(message);
    }
}
