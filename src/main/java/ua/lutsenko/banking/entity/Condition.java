package ua.lutsenko.banking.entity;

import java.sql.Date;

/**
 * Created by Denis Lutsenko.
 */
public class Condition {
    private Integer id;

    private final Account account;
    private final String type;
    private Date deadLine;
    private double percentOfWithdrawal;
    private double monthlyPercent;

    public Condition(Account account, String type) {
        this.account = account;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
