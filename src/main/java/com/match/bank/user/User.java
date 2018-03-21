package com.match.bank.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.match.bank.account.Account;

public class User implements Serializable, Comparable<User> {

    /**
     * 
     */
    private static final long serialVersionUID = -1283469865950068932L;
    private Integer userId;
    private String email;
    private String address;

    private List<Account> accounts = new ArrayList<Account>();

    public User(Integer userId, String email, String address) {
        if (userId == null)
            throw new NullPointerException();
        if (!UserUtils.validEmailAddress(email))
            throw new IllegalArgumentException();
        this.userId = userId;
        this.email = email;
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, email=%s address=%s]", userId, email, address);
    }

    @Override
    public int compareTo(User u) {
        return userId.compareTo(u.userId);
    }
}
