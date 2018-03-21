package com.match.bank.user;

public class UserUtils {

    public static boolean validEmailAddress(String email) {
        if (email == null)
            return false;
        if (email.equals(""))
            return false;
        if (!email.contains("@"))
            return false;
        return true;
    }

}
