package com.match.bank.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.match.bank.account.Account;
import com.match.bank.data.Data;
import com.match.bank.exception.AccountNotFoundException;
import com.match.bank.exception.DataLayerException;
import com.match.bank.exception.InsufficientFundsException;
import com.match.bank.exception.UserNotFoundException;

public class PersistedBankService implements BankService {

    static Logger log = Logger.getLogger(PersistedBankService.class);
    private static PersistedBankService instance;
    private Data data;

    private PersistedBankService() {
    }

    public static synchronized PersistedBankService getInstance() {
        if (instance == null) {
            instance = new PersistedBankService();
            instance.setData(new Data());
        }
        return instance;
    }

    private void setData(Data data) {
        this.data = data;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account deposit(Integer accountId, Integer amount) throws DataLayerException, AccountNotFoundException {
        log.info("Depositing " + amount + " into account: " + accountId);
        Account account = data.getAccountByAccountId(accountId);
        return account = data.deposit(account, new BigDecimal(amount));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account withdraw(Integer accountId, Integer amount)
            throws InsufficientFundsException, DataLayerException, AccountNotFoundException {
        log.info("Making withdrawal of " + amount + " from account: " + accountId);
        Account account = data.getAccountByAccountId(accountId);
        return account = data.withdraw(account, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account retrieveAccountByAccountId(Integer accountId) throws DataLayerException, AccountNotFoundException {
        log.info("Retrieving account for accountId: " + accountId);
        return data.getAccountByAccountId(accountId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Account> retrieveAccountsByUserId(Integer userId) throws DataLayerException, UserNotFoundException {
        log.info("Retrieving accounts for user " + userId);
        List<Account> accounts = data.getAccountsByUserId(userId);
        if (accounts.isEmpty()) {
            throw new UserNotFoundException("No accounts exist for " + userId);
        }
        return accounts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account openAccount(Integer userId, Integer initialBalance) throws DataLayerException {
        log.info("Opening account for user: " + userId + " with balance: " + initialBalance);
        return data.openAccount(userId, initialBalance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAccount(Integer accountId) throws DataLayerException {
        log.info("Deleting account: " + accountId);
        data.deleteAccount(accountId);
    }

}
