package ua.lutsenko.banking.entity;

import java.sql.Date;

/**
 * Created by Denis Lutsenko on 7/30/2016.
 */
public class Account {
    private Integer id;
    private final User user;
    private final String accountCode;
    private final String currency;


    private Date openingDate;
    private double currentBalance;
    private double defaultBalance;
    private boolean isBlocked;

     public Account(User user, String accountCode, String currency){
        this.user = user;
        this.accountCode = accountCode;
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public String getCurrency() {
        return currency;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getDefaultBalance() {
        return defaultBalance;
    }

    public void setDefaultBalance(double defaultBalance) {
        this.defaultBalance = defaultBalance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

}
