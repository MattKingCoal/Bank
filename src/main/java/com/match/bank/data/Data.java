package com.match.bank.data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.match.bank.account.Account;
import com.match.bank.account.AccountType;
import com.match.bank.exception.AccountNotFoundException;
import com.match.bank.exception.DataLayerException;
import com.match.bank.exception.InsufficientFundsException;

public class Data {

    static Logger log = Logger.getLogger(Data.class);
    private static Connection conn;

    public Data() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + "localhost" + "/" + "fileshark" + "?" + "user="
                    + "match" + "&password=" + "match1q2w#E$R");
            log.debug("DB connection created.....");
        } catch (SQLException e) {
            log.debug(e.getMessage());
        }
    }

    public Account getAccountByAccountId(int accountId) throws DataLayerException, AccountNotFoundException {
        log.debug("Looking for account with id: " + accountId);
        Account account = null;
        PreparedStatement pstatement = null;
        ResultSet rs = null;
        try {
            pstatement = conn.prepareStatement(SQLQueries.FINDACCOUNTBYACCOUNTID);
            pstatement.setInt(1, accountId);
            rs = pstatement.executeQuery();
            if (rs.next()) {
                account = constructAccount(rs);
            } else {
                log.error("No account found for id: " + accountId);
                throw new AccountNotFoundException("No account found for Id: " + accountId);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataLayerException(e.getMessage());
        } finally {
            DbUtils.closeQuietly(pstatement);
            DbUtils.closeQuietly(rs);
        }
        return account;
    }

    public List<Account> getAccountsByUserId(Integer userId) throws DataLayerException {
        log.debug("Looking for accounts for user: " + userId);
        List<Account> accounts = new ArrayList<>();
        String findAccountsByUserIdSql = SQLQueries.FINDACCOUNTSBYUSERID + userId;
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(findAccountsByUserIdSql);
            while (rs.next()) {
                Account account;
                account = constructAccount(rs);
                accounts.add(account);
                log.debug("Found: " + account);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataLayerException(e.getMessage());
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(rs);
        }
        Collections.sort(accounts);
        return accounts;
    }

    public Account deposit(Account account, BigDecimal amount) throws DataLayerException {
        account.deposit(amount);
        persistBalance(account);
        log.debug("Deposit of " + amount + " succesful || " + account);
        return account;
    }

    public Account withdraw(Account account, Integer amount) throws InsufficientFundsException, DataLayerException {
        account.withdraw(amount);
        persistBalance(account);
        log.debug("Withdrawal of " + amount + " succesful || " + account);
        return account;
    }

    private void persistBalance(Account account) throws DataLayerException {
        PreparedStatement pstatement = null;
        try {
            pstatement = conn.prepareStatement(SQLQueries.UPDATEBALANCEBYACCOUNTID);
            pstatement.setBigDecimal(1, account.getBalance());
            pstatement.setInt(2, account.getAccountID());
            pstatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataLayerException(e.getMessage());
        } finally {
            DbUtils.closeQuietly(pstatement);
        }
    }

    private Account constructAccount(ResultSet result) throws SQLException {
        AccountType type;
        String typeStr = result.getString(4);
        if ("S".equals(typeStr))
            type = AccountType.SAVING;
        else
            type = AccountType.CURRENT;
        Account account = new Account(result.getInt(1), result.getInt(2), result.getBigDecimal(3), type);
        log.debug("Constructed from database: " + account);
        return account;
    }

    public Account openAccount(int ssid, int balance) throws DataLayerException {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance can't be negative: " + balance);
        }
        PreparedStatement pstatement = null;
        try {
            pstatement = conn.prepareStatement(SQLQueries.CREATENEWACCOUNT);
            pstatement.setInt(1, ssid);
            pstatement.setBigDecimal(2, new BigDecimal(balance));
            pstatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataLayerException(e.getMessage());
        } finally {
            DbUtils.closeQuietly(pstatement);
        }
        List<Account> accounts = getAccountsByUserId(ssid);
        Account account = accounts.get(0);
        log.debug("Account succesfully created - " + account);
        return account;
    }

    public void deleteAccount(int accId) throws DataLayerException {
        PreparedStatement pstatement = null;
        try {
            pstatement = conn.prepareStatement(SQLQueries.DELETEACCOUNT);
            pstatement.setInt(1, accId);
            pstatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataLayerException(e.getMessage());
        } finally {
            DbUtils.closeQuietly(pstatement);
        }
        log.debug("Account succesfully deleted: " + accId);
    }
}
