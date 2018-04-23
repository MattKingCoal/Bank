package com.match.bank.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.match.bank.account.Account;
import com.match.bank.account.AccountType;
import com.match.bank.data.Data;
import com.match.bank.exception.AccountNotFoundException;
import com.match.bank.exception.InsufficientFundsException;

public class BankServiceTest {

    @InjectMocks
    private static BankService service;

    @Mock
    private static Data data;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeClass
    public static void setupService() {
        service = new PersistedBankService();
    }

    @Test
    public void testWithdraw() throws Exception {
        Account test = new Account(123, 20327866, new BigDecimal(500), AccountType.CURRENT);
        when(data.getAccountByAccountId(test.getAccountID())).thenReturn(test);
        Account account = service.withdraw(test.getAccountID(), 100);

        assertTrue((account.getBalance().intValue() == 400));

        verify(data).getAccountByAccountId(test.getAccountID());
        verify(data).persistBalance(test);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawTooMuchThrowsInsufficientFunds() throws Exception {
        Account test = new Account(123, 20327866, new BigDecimal(500), AccountType.CURRENT);
        when(data.getAccountByAccountId(test.getAccountID())).thenReturn(test);

        service.withdraw(test.getAccountID(), 501);
    }

    @Test(expected = AccountNotFoundException.class)
    public void testWithdrawNonExistingAccountThrosAccountNotFound() throws Exception {
        int invalidAccountId = 666;

        when(data.getAccountByAccountId(invalidAccountId))
                .thenThrow(new AccountNotFoundException("No account found for Id: "));

        service.withdraw(invalidAccountId, 100);
    }

    @Test
    public void testDeposit() throws Exception {
        Account test = new Account(123, 20327866, new BigDecimal(500), AccountType.CURRENT);
        when(data.getAccountByAccountId(test.getAccountID())).thenReturn(test);

        Account account = service.deposit(test.getAccountID(), 100);

        assertTrue((account.getBalance().intValue() == 600));

        verify(data).getAccountByAccountId(test.getAccountID());
    }
}
