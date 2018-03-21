package com.match.bank.account;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class EqualityTest {

    @Test
    public void testEqualityMethod() {
        HashMap<Account, String> accountSet = new HashMap<Account, String>();

        Account one = new Account(12345, 678910, new BigDecimal(100.0), AccountType.CURRENT);
        Account two = new Account(12345, 678910, new BigDecimal(100.0), AccountType.CURRENT);
        accountSet.put(one, "First");
        Assert.assertTrue(accountSet.containsKey(two));
    }
}
