package com.match.bank.account;

import java.io.Serializable;
import java.math.BigDecimal;

import com.match.bank.exception.InsufficientFundsException;

public class Account implements Comparable<Account>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4988890835158727342L;
    private final Integer accountID;
    private final Integer UserId;
    private final AccountType type;
    private BigDecimal balance = new BigDecimal(0);

    public Account(Integer accountID, Integer userID, BigDecimal initialbalance, AccountType type) {
        this.accountID = accountID;
        this.UserId = userID;
        this.type = type;
        deposit(initialbalance);
    }

    public Integer getUserId() {
        return UserId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public BigDecimal withdraw(Integer value) throws InsufficientFundsException {
        if (value == null)
            throw new NullPointerException();
        if (value < 0)
            throw new IllegalArgumentException("seriously, stop that, value can't be negative: " + value);
        if (value > balance.intValue())
            throw new InsufficientFundsException("Insufficient funds......");
        balance = balance.subtract(new BigDecimal(value));
        return balance;
    }

    public BigDecimal deposit(BigDecimal initialbalance) {
        if (initialbalance == null)
            throw new NullPointerException();
        if (initialbalance.intValue() < 0)
            throw new IllegalArgumentException("seriously, stop that, value can't be negative: " + initialbalance);
        balance = balance.add(initialbalance);
        return balance;
    }

    public String toAccountXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<account>\n");
        sb.append("<account-id>");
        sb.append(accountID);
        sb.append("</account-id>\n");
        sb.append("<user-id>");
        sb.append(UserId);
        sb.append("</user-id>\n");
        sb.append("<balance>");
        sb.append(balance);
        sb.append("</balance>\n");
        sb.append("<account-type>");
        sb.append(type);
        sb.append("</account-type>\n");
        sb.append("</account>");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        Account other = (Account) o;
        return accountID.equals(other.accountID);

    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int result = 1;
        result = prime * (result + ((accountID == null) ? 0 : accountID));
        return result;
    }

    @Override
    public String toString() {
        // return String.format("Account: [id=%s, Type=%s, userId=%s, balance=%s]", accountID, type, UserId, balance);
        return String.format("Account: [id=%s, userId=%s, balance=%s]", accountID, UserId, balance);
    }

    @Override
    public int compareTo(Account o) {
        return -accountID.compareTo(o.accountID);
    }

}
