package com.match.bank.exception;

public class InsufficientFundsException extends BankCheckedException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InsufficientFundsException(String message) {
        super(message);
    }

}
