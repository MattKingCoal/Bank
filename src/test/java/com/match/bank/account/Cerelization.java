package com.match.bank.account;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

import org.junit.Test;

import com.match.bank.user.BusinessUser;
import com.match.bank.user.User;

public class Cerelization {

    @Test
    public void cereal() throws Exception {
        File f = new File("account.ser");
        Account account = new Account(1234, 10000, new BigDecimal("100.00"), AccountType.CURRENT);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(account);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        Account read = (Account) ois.readObject();
        ois.close();
        f.delete();
        System.out.println(read);
        System.out.println("HC: " + account.hashCode() + ", " + read.hashCode());
        assertEquals(account, read);
    }

    @Test
    public void cerealiseUser() throws Exception {
        User bizUser = new BusinessUser(123, "cool@hpi.com", "Galway", "RTE");
        System.out.println(bizUser.getClass().getName());
        File f = new File("user.ser");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(bizUser);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        BusinessUser read = (BusinessUser) ois.readObject();
        System.out.println(read.getClass().getName());
        ois.close();
        f.delete();
        System.out.println(read);
        assertEquals(bizUser.toString(), read.toString());
    }

}
