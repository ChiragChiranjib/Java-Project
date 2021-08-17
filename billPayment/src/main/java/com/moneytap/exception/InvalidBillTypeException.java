package com.moneytap.exception;

public class InvalidBillTypeException extends Exception {
    public InvalidBillTypeException() {
    }

    public InvalidBillTypeException(String message) {
        super(message);
    }
}
