package com.match.bank.account;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.match.bank.exception.InsufficientFundsException;

public class AccountTest {

    @Test
    public void testCreateCurrentAccount() {
        Account account = new Account(123, 456, new BigDecimal(100), AccountType.CURRENT);
        assertNotNull(account);
    }

    @Test
    public void testDeposit() {
        Account account = new Account(123, 1234, new BigDecimal(100), AccountType.CURRENT);
        account.deposit(new BigDecimal(20));
        assertTrue(account.getBalance().intValue() == 120);
    }

    @Test
    public void testWithdraw() throws Exception {
        Account current = new Account(123, 456, new BigDecimal(100), AccountType.CURRENT);
        current.withdraw(20);
        assertTrue(current.getBalance().intValue() == 80);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testAttemptToWithdrawTooMuch() throws Exception {
        Account account = new Account(123, 456, new BigDecimal(100), AccountType.CURRENT);
        account.withdraw(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeInitialValue() {
        Account account = new Account(122, 666, new BigDecimal(-12378), AccountType.CURRENT);
        account.getBalance();
    }
}
