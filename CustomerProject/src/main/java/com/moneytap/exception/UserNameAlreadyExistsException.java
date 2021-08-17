package com.moneytap.exception;

public class UserNameAlreadyExistsException extends Exception{
    public UserNameAlreadyExistsException() {
    }

    public UserNameAlreadyExistsException(String message) {
        super(message);
    }
}
