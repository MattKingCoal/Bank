package com.match.bank.service;

import com.match.bank.data.Data;

public class PersistedBankServiceFactory {

    private static PersistedBankService instance = null;

    public static synchronized BankService getInstance() {
        if (instance == null) {
            instance = new PersistedBankService();
            instance.setData(new Data());
        }
        return instance;
    }

}
