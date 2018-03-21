package com.match.bank.exception;

public class AccountNotFoundException extends BankCheckedException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AccountNotFoundException(String message) {
        super(message);

    }

}
