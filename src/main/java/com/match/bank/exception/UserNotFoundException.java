package com.match.bank.exception;

public class UserNotFoundException extends BankCheckedException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }

}
