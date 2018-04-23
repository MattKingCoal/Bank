package com.match.bank.account;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Comparable<Account>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8780206051169526183L;

    private final Integer accountID;
    private final Integer UserId;
    private final AccountType type;
    private BigDecimal balance = new BigDecimal(0);

    public Account(Integer accountID, Integer userID, BigDecimal initialbalance, AccountType type) {
        if (initialbalance.intValue() < 0) {
            throw new IllegalArgumentException();
        }
        this.accountID = accountID;
        this.UserId = userID;
        this.type = type;
        this.balance = initialbalance;
    }

    public Integer getUserId() {
        return UserId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getAccountID() {
        return accountID;
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
