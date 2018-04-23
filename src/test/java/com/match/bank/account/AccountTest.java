package com.match.bank.account;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

public class AccountTest {

    @Test
    public void testCreateCurrentAccount() {
        Account account = new Account(123, 456, new BigDecimal(100), AccountType.CURRENT);
        assertNotNull(account);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeInitialValue() {
        Account account = new Account(122, 666, new BigDecimal(-12378), AccountType.CURRENT);
        account.getBalance();
    }
}
