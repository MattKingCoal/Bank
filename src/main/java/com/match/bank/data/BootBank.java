package com.match.bank.data;

import java.util.List;

import com.match.bank.account.Account;
import com.match.bank.exception.DataLayerException;

public class BootBank {

    public static void main(String[] args) throws DataLayerException {
        // new BootBank().go("localhost", "fileshark", "match", "match1q2w#E$R");
        Data accessor = new Data();
        List<Account> accounts = accessor.getAccountsByUserId(20327866);
        // Collections.sort(accounts);
        // Collections.reverse(accounts);
        // Collections.sort(accounts);
        for (Account a : accounts) {
            System.out.println(a);
        }
    }
}
