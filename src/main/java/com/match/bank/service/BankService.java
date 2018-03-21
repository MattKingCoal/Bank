package com.match.bank.service;

import java.util.List;

import com.match.bank.account.Account;
import com.match.bank.exception.AccountNotFoundException;
import com.match.bank.exception.DataLayerException;
import com.match.bank.exception.InsufficientFundsException;
import com.match.bank.exception.UserNotFoundException;

/**
 * Interface of the bank of match service
 * 
 * 
 * Provides methods to open & close accounts. Retrieve Account object(s) based on acountId or userId
 * 
 * 
 */
public interface BankService {

    /**
     * 
     * Deposit money into a Bank of Match account.
     * 
     * @param accountId (Unique account identifier)
     * @param amount (The amount to deposit as an Integer)
     * @return Account (The account object following the deposit)
     * @throws DataLayerException
     * @throws AccountNotFoundException
     */
    public Account deposit(Integer accountId, Integer amount) throws DataLayerException, AccountNotFoundException;

    /**
     * 
     * Withdraw money from a Bank of Match account.
     * 
     * @param accountId (Unique account identifier)
     * @param amount (The amount to withdraw as an Integer)
     * @return Account (The account object following the withdrawal)
     * @throws DataLayerException
     * @throws InsufficientFundsException
     * @throws AccountNotFoundException
     */
    public Account withdraw(Integer accountId, Integer amount)
            throws DataLayerException, InsufficientFundsException, AccountNotFoundException;

    /**
     * 
     * Returns an Account object for a given account ID Integer.
     * 
     * @param accountId (Unique account identifier)
     * @return Account (The account object)
     * @throws DataLayerException
     * @throws AccountNotFoundException
     */
    public Account retrieveAccountByAccountId(Integer accountId) throws DataLayerException, AccountNotFoundException;

    /**
     * 
     * Returns a List of Accounts belonging to a user. If no accounts exist the List will be empty.
     * 
     * @param userId (Unique userId of the user as an Integer)
     * @return List of Account objects owned by the user
     * @throws DataLayerException
     * @throws UserNotFoundException
     */
    public List<Account> retrieveAccountsByUserId(Integer userId) throws DataLayerException, UserNotFoundException;

    /**
     * 
     * Open a Bank of Match account.
     * 
     * @param userId (Unique userId of the user)
     * @param initialBalance (Initial amount for the Account)
     * @return Account (The account object after it has been opened)
     * @throws DataLayerException
     */
    public Account openAccount(Integer userId, Integer initialBalance) throws DataLayerException;

    /**
     * 
     * Delete a Bank of Match account.
     * 
     * @param accountId (Unique ID of the account)
     * @throws DataLayerException
     * @throws AccountNotFoundException
     */
    public void deleteAccount(Integer accountId) throws DataLayerException, AccountNotFoundException;
}
