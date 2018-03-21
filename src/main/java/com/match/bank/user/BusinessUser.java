package com.match.bank.user;

public class BusinessUser extends User {

    /**
     * 
     */
    private static final long serialVersionUID = -8894164580193466694L;
    private String companyName;

    public BusinessUser(Integer userId, String email, String address, String companyName) {
        super(userId, email, address);
        this.setCompanyName(companyName);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void businessyStuff() {

    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder(super.toString());
        sbr.deleteCharAt(sbr.length() - 1);
        sbr.append(" " + String.format("Company=%s]", companyName));
        return (sbr.toString());
    }
}
