package ua.lutsenko.banking.entity;
import java.sql.Date;

/**
 * Created by Denis Lutsenko.
 */
public class Application {
    private Integer id;
    private final User user;
    private final String type;
    private final String currency;
    private final Date date;

    private Double balance;
    private String status;

    public Application(User user, String type, String currency, Date date) {
        this.user = user;
        this.type = type;
        this.currency = currency;
        this.date = date;
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

    public String getType() {
        return type;
    }

    public String getCurrency() {
        return currency;
    }

    public Date getDate() {
        return date;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
