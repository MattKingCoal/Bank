package com.match.bank.data;

public class SQLQueries {

    public static final String UPDATEBALANCEBYACCOUNTID = "update tbl_bnk_acct set blnc = ? where acct_id = ?";
    public static final String FINDACCOUNTSBYUSERID = "select * from tbl_bnk_acct where usr_id = ";
    public static final String CREATENEWACCOUNT = "insert into tbl_bnk_acct (usr_id, blnc, acct_typ) values (?, ?, 'C')";
    public static final String DELETEACCOUNT = "delete from tbl_bnk_acct where acct_id = ?";
    public static final String FINDACCOUNTBYACCOUNTID = "select * from tbl_bnk_acct where acct_id = ?";

}
