package com.moneytap.exception;

public class SelfBeneficiaryException extends Exception {
    public SelfBeneficiaryException() {
    }

    public SelfBeneficiaryException(String message) {
        super(message);
    }
}
