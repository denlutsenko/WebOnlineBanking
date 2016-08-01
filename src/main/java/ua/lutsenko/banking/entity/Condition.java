package ua.lutsenko.banking.entity;

import java.sql.Date;

/**
 * Created by Denis Lutsenko on 7/30/2016.
 */
public class Condition {
    private int id;

    private final Account account;
    private final String type;
    private Date deadLine;
    private double percentOfWithdrawal;
    private double monthlyPercent;


    public Condition(Account account, String type) {
        this.account = account;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public String getType() {
        return type;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public double getPercentOfWithdrawal() {
        return percentOfWithdrawal;
    }

    public void setPercentOfWithdrawal(double percentOfWithdrawal) {
        this.percentOfWithdrawal = percentOfWithdrawal;
    }

    public double getMonthlyPercent() {
        return monthlyPercent;
    }

    public void setMonthlyPercent(double monthlyPercent) {
        this.monthlyPercent = monthlyPercent;
    }
}
